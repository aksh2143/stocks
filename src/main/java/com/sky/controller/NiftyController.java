package com.sky.controller;

import com.sky.feign.FeignClientNSENifty;
import com.sky.service.DataPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class NiftyController {


    @Autowired
    FeignClientNSENifty feignClientNSENifty;
    @Autowired
    DataPrepareService dataPrepareService;

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "index";
    }

    @RequestMapping("/next")
    public String next(Map<String, Object> model) {
        model.put("message", "You are in new page !!");
        return "next";
    }

    /*@GetMapping("/")
    public String welcome(Model model) {
        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        ApplicationStaticData.expiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        Map<String, String> strikesMap = new HashMap<>();
        for (String strike : ApplicationStaticData.expiryDates) {
            strikesMap.put(strike, strike);
        }
        ApplicationStaticData.expiryDatesMap = strikesMap;

        model.addAttribute("expiries", ApplicationStaticData.expiryDatesMap);
        return "welcome";
    }*/
}
