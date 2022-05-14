package com.sky.stocks.controller;

import com.sky.stocks.entity.nse.ApplicationStaticData;
import com.sky.stocks.entity.nse.Instrument;
import com.sky.stocks.entity.nse.NSE;
import com.sky.stocks.feign.FeignBuilder;
import com.sky.stocks.feign.FeignClientNSENifty;
import com.sky.stocks.service.DataPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    FeignClientNSENifty feignClientNSENifty;
    @Autowired
    DataPrepareService dataPrepareService;
    public static Map<String, String> headersMap;
    {
       headersMap = FeignBuilder.builder();
    }

    @GetMapping("/getniftydata")
    public ResponseEntity<Instrument> getOptionChainAnalysis(@RequestParam("expiryDate")String expiryDate) throws Exception {
        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        System.out.println(nse);
        ApplicationStaticData.expiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if(!ApplicationStaticData.expiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date");

        return ResponseEntity.ok(dataPrepareService.prepareInstrumentData(nse,50, 200, false, expiryDate));
    }
}
