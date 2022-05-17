package com.sky.controller;

import com.sky.entity.nse.User;
import com.sky.feign.FeignClientNSENifty;
import com.sky.service.DataPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class TestController {


    @Autowired
    FeignClientNSENifty feignClientNSENifty;
    @Autowired
    DataPrepareService dataPrepareService;
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        User user = new User();
        user.setFavoriteFrameworks((new String[]{"Spring MVC", "Struts 2"}));
        user.setGender("M");
        ModelAndView modelAndView = new ModelAndView("user", "command", user);
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb") User user,
                          ModelMap model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("receivePaper", user.isReceivePaper());
        model.addAttribute("favoriteFrameworks", user.getFavoriteFrameworks());
        model.addAttribute("gender", user.getGender());
        model.addAttribute("favoriteNumber", user.getFavoriteNumber());
        model.addAttribute("country", user.getCountry());
        return "users";
    }

    @ModelAttribute("webFrameworkList")
    public List<String> getWebFrameworkList() {
        List<String> webFrameworkList = new ArrayList<String>();
        webFrameworkList.add("Spring MVC");
        webFrameworkList.add("Struts 1");
        webFrameworkList.add("Struts 2");
        webFrameworkList.add("Apache Wicket");
        return webFrameworkList;
    }

    @ModelAttribute("numbersList")
    public List<String> getNumbersList() {
        List<String> numbersList = new ArrayList<String>();
        numbersList.add("1");
        numbersList.add("2");
        numbersList.add("3");
        numbersList.add("4");
        return numbersList;
    }

    @ModelAttribute("countryList")
    public Map<String, String> getCountryList() {
        Map<String, String> countryList = new HashMap<String, String>();
        countryList.put("US", "United States");
        countryList.put("CH", "China");
        countryList.put("SG", "Singapore");
        countryList.put("MY", "Malaysia");
        return countryList;
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
