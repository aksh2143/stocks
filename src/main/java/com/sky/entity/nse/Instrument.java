package com.sky.entity.nse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Instrument {
    String symbol;
    String name;
    String expiryDate;
    double spotValue;
    double currentStrike;
    String futureValue;
    OptionChain optionChain;
    String country;
}
