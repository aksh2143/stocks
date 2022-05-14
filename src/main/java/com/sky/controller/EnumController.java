package com.sky.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/enum")
public class EnumController {

    @Value("com.sky.enums")
    private String enumPackage;

    /*public EntityResponse getClassMap(@PathVariable String classname,
                                      @RequestParam(value = "actionName", required = true) String actionName) {
        return Commonu
    }*/
}
