package com.sky.controller;

import com.sky.entity.nse.ApplicationStaticData;
import com.sky.entity.nse.Instrument;
import com.sky.entity.nse.NSE;
import com.sky.feign.FeignBuilder;
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
@CrossOrigin(origins = "http://localhost:4200")
public class NiftyRestController {

    @Autowired
    FeignClientNSENifty feignClientNSENifty;
    @Autowired
    DataPrepareService dataPrepareService;

    @GetMapping("/nifty/getniftyexpirydatesmap")
    public ResponseEntity<Map<String, String>> getExpiryDatesList() throws Exception {
     //   NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
     //   ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());
        return ResponseEntity.ok(ApplicationStaticData.niftyExpiryDatesMap);
    }

    @GetMapping("/nifty/getniftyexpirydateslist")
    public ResponseEntity<List<String>> getExpiryDatesMap() throws Exception {
      //  NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
      //  ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());
        return ResponseEntity.ok(ApplicationStaticData.niftyExpiryDates);
    }

    @GetMapping("/nifty/getniftydata")
    public ResponseEntity<Instrument> getOptionChainAnalysis(@RequestParam("expiryDate") String expiryDate) throws Exception {
        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.niftyExpiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date: "+expiryDate);

        return ResponseEntity.ok(dataPrepareService.prepareInstrumentData(nse, 50, 1000, false, expiryDate));
    }

    @GetMapping("/nifty/getniftydatalist")
    public ResponseEntity<List<Instrument>> getOptionChainAnalysisList(@RequestParam("expiryDate") String expiryDate) throws Exception {
        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.niftyExpiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date: "+expiryDate);

        Instrument i = dataPrepareService.prepareInstrumentData(nse, 50, 1000, false, expiryDate);
        List<Instrument> il = new ArrayList<>(); il.add(i);
        return ResponseEntity.ok(il);
    }
}
