package com.sky.controller;

import com.sky.entity.nse.ApplicationStaticData;
import com.sky.entity.nse.Instrument;
import com.sky.entity.nse.NSE;
import com.sky.entity.nse.Strike;
import com.sky.feign.FeignBuilder;
import com.sky.feign.FeignClientNSENifty;
import com.sky.service.DataPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class NiftyController {

    @Autowired
    FeignClientNSENifty feignClientNSENifty;
    @Autowired
    DataPrepareService dataPrepareService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView nifty() {
        Instrument instrument = Instrument.builder().build();
        ModelAndView modelAndView = new ModelAndView("index", "command", instrument);
        return modelAndView;
    }

    @ModelAttribute("getexpirydates")
    public Map<String, String> egtExpiryDates() {
        return ApplicationStaticData.niftyExpiryDatesMap;
    }

    /*@RequestMapping(value = "/nifty/getoptionchain", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("expiryDate") String expiryDate,
                                ModelMap model) throws Exception {

        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.niftyExpiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date");

        Instrument instrument = dataPrepareService.prepareInstrumentData(nse, 50, 200, false, expiryDate);
        List<Strike> list = new ArrayList<>();
        Strike s1 = Strike.builder().strikePrice(1200).build();
        Strike s2 = Strike.builder().strikePrice(100).build();
        list.add(s1);
        list.add(s2);


        ModelAndView modelAndView = new ModelAndView("index", "command", instrument);
        return modelAndView;
    }*/

    @RequestMapping(value = "/nifty/getoptionchain")
    public String addUser(@ModelAttribute("expiryDate") String expiryDate,
                          Model model) throws Exception {

        NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
        ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

        if (!ApplicationStaticData.niftyExpiryDates.contains(expiryDate))
            throw new Exception("Invalid Expiry Date");

        Instrument instrument = dataPrepareService.prepareInstrumentData(nse, 50, 200, false, expiryDate);
        List<Strike> list = new ArrayList<>();
        Strike s1 = Strike.builder().strikePrice(1200).build();
        Strike s2 = Strike.builder().strikePrice(100).build();
        list.add(s1);
        list.add(s2);

        model.addAttribute("instrument", instrument);

        return "viewemp";
    }


    @RequestMapping("/viewemp")
    public String viewemp(Model m) {
        List<Strike> list = new ArrayList<>();
        Strike s1 = Strike.builder().strikePrice(1200).build();
        Strike s2 = Strike.builder().strikePrice(100).build();
        list.add(s1);
        list.add(s2);
        m.addAttribute("list", list);
        //return "viewemp";
        return "index";
    }
}
