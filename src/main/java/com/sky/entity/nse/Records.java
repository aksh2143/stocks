package com.sky.entity.nse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Records {
    String[] expiryDates;
    Data[] data;
    String timestamp;
    double underlyingValue;
    double strikePrices[];
}
