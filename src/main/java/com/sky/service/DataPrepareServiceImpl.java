package com.sky.service;

import com.sky.entity.nse.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataPrepareServiceImpl implements DataPrepareService {
    public Instrument prepareInstrumentData(NSE nse, double round, double strikeRange, boolean current, String expiryDate) throws Exception {

        if (current == false && !StringUtils.hasText(expiryDate)) {
            throw new Exception("Please enter valid expiry date");
        }

        List<Data> records = new ArrayList<Data>();
        String expiry = "";

        double currentStrike = Math.round((nse.getRecords().getUnderlyingValue() / round)) * round;
        double spotPrice = nse.getRecords().getUnderlyingValue();

        if (current) {
            records = Arrays.asList(nse.getFiltered().getData()).stream()
                    .filter(data -> (data.getStrikePrice() >= (currentStrike - strikeRange) && data.getStrikePrice() <= (currentStrike + strikeRange)))
                    .collect(Collectors.toList());
        } else {
            records = Arrays.asList(nse.getRecords().getData()).stream()
                    .filter(data -> (data.getExpiryDate().equalsIgnoreCase(expiryDate) && data.getStrikePrice() >= (currentStrike - strikeRange) && data.getStrikePrice() <= (currentStrike + strikeRange)))
                    .collect(Collectors.toList());
        }

        Data firstData = records.get(0);
        expiry = firstData.getExpiryDate();

        List<Strike> strikeList = new ArrayList<>();
        for (Data record : records) {
            Strike strike = Strike.builder()
                    .strikePrice(record.getStrikePrice())
                    .ce(record.getCe())
                    .pe(record.getPe())
                    .build();
            strikeList.add(strike);
        }

        for (Strike strike : strikeList) {
            CE ce = strike.getCe();
            double cedistanceFromSpot = currentStrike - ce.getStrikePrice();
            if (cedistanceFromSpot <= 0) cedistanceFromSpot = 0;
            double ceair = ce.getLastPrice() - cedistanceFromSpot;
            double cechips = cedistanceFromSpot;
            ce.setChips(cechips);
            ce.setAir(ceair);
            if (ce.getStrikePrice() < currentStrike) {
                ce.setItm(true);
                ce.setPlace("ITM");
            } else if (ce.getStrikePrice() == currentStrike) {
                ce.setAtm(true);
                ce.setPlace("ATM");
            } else {
                ce.setOtm(true);
                ce.setPlace("OTM");
            }
            strike.setCe(ce);

            PE pe = strike.getPe();
            double pedistanceFromSpot = currentStrike - pe.getStrikePrice();
            if (pedistanceFromSpot > 0) pedistanceFromSpot = 0;
            double peair = pe.getLastPrice() + pedistanceFromSpot;
            double pechips = Math.abs(pedistanceFromSpot);
            pe.setChips(pechips);
            pe.setAir(peair);
            if (pe.getStrikePrice() < currentStrike) {
                pe.setOtm(true);
                pe.setPlace("OTM");
            } else if (ce.getStrikePrice() == currentStrike) {
                pe.setAtm(true);
                pe.setPlace("ATM");
            } else {
                ce.setItm(true);
                pe.setPlace("ITM");
            }
            strike.setPe(pe);
        }

        OptionChain optionChain = OptionChain.builder()
                .strikeList(strikeList)
                .expiryDate(expiry)
                .build();

        Instrument instrument = Instrument.builder()
                .symbol(firstData.getCe().getUnderlying())
                .spotValue(spotPrice)
                .currentStrike(currentStrike)
                .optionChain(optionChain)
                .expiryDate(expiryDate)
                .build();
        return instrument;
    }
}
