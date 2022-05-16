package com.sky.controller;

import com.sky.entity.nse.ApplicationStaticData;
import com.sky.entity.nse.Instrument;
import com.sky.entity.nse.NSE;
import com.sky.feign.FeignBuilder;
import com.sky.feign.FeignClientNSENifty;
import com.sky.service.DataPrepareService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Api
@RestController("/rest")
public class NiftyRestController {

    @Autowired
    FeignClientNSENifty feignClientNSENifty;
    @Autowired
    DataPrepareService dataPrepareService;

    @GetMapping("/getexpirydates")
    public ResponseEntity<List<String>> getExpiryDates() throws Exception {
        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        ApplicationStaticData.expiryDates = Arrays.asList(nse.getRecords().getExpiryDates());
        return ResponseEntity.ok(ApplicationStaticData.expiryDates);
    }

    @GetMapping("/getniftydata")
    public ResponseEntity<Instrument> getOptionChainAnalysis(@RequestParam("expiryDate") String expiryDate) throws Exception {
        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        System.out.println(nse);
        ApplicationStaticData.expiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.expiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date");

        return ResponseEntity.ok(dataPrepareService.prepareInstrumentData(nse, 50, 200, false, expiryDate));
    }
}
