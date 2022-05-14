package com.sky.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum InstrumentType {
    INDEX("INDEX", "Index"),
    STOCK("STOCK", "Stock");

    String code;
    String name;

    InstrumentType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<InstrumentType> list() {
        return Arrays.asList(INDEX, STOCK);
    }

    public static Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        list().forEach(element -> map.put(element.getCode(), element.getName()));
        return map;
    }
}
