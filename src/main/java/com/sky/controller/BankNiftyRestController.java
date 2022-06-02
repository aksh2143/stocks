package com.sky.controller;

import com.sky.entity.nse.ApplicationStaticData;
import com.sky.entity.nse.Instrument;
import com.sky.entity.nse.NSE;
import com.sky.feign.FeignBuilder;
import com.sky.feign.FeignClientNSEBankNifty;
import com.sky.feign.FeignClientNSENifty;
import com.sky.service.DataPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController()
@CrossOrigin(origins = "*")
public class BankNiftyRestController {

    public static final int bankNiftyStrikeRange = 5000;

    @Autowired
    FeignClientNSEBankNifty feignClientNSEBankNifty;
    @Autowired
    DataPrepareService dataPrepareService;

    @GetMapping("/bnf/getbnfyexpirydatesmap")
    public ResponseEntity<Map<String, String>> getExpiryDatesList() throws Exception {
        return ResponseEntity.ok(ApplicationStaticData.bnfExpiryDatesMap);
    }

    @GetMapping("/bnf/getniftyexpirydateslist")
    public ResponseEntity<List<String>> getExpiryDatesMap() throws Exception {
        return ResponseEntity.ok(ApplicationStaticData.bnfExpiryDates);
    }

    @GetMapping("/bnf/getbnfdata")
    public ResponseEntity<Instrument> getOptionChainAnalysis(@RequestParam("expiryDate") String expiryDate) throws Exception {
        NSE nse = feignClientNSEBankNifty.getLiveBankNiftyData(FeignBuilder.builder());
        ApplicationStaticData.bnfExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.bnfExpiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date: "+expiryDate);
Instrument instru = dataPrepareService.prepareInstrumentData(nse, 100, bankNiftyStrikeRange, false, expiryDate);
        return ResponseEntity.ok(instru);
    }

    @GetMapping("/bnf/getbnfdatalist")
    public ResponseEntity<List<Instrument>> getOptionChainAnalysisList(@RequestParam("expiryDate") String expiryDate) throws Exception {
        NSE nse = feignClientNSEBankNifty.getLiveBankNiftyData(FeignBuilder.builder());
        ApplicationStaticData.bnfExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.bnfExpiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date: "+expiryDate);

        Instrument i = dataPrepareService.prepareInstrumentData(nse, 50, bankNiftyStrikeRange, false, expiryDate);
        List<Instrument> il = new ArrayList<>(); il.add(i);
        return ResponseEntity.ok(il);
    }
}
