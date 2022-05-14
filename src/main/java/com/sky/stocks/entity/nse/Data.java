package com.sky.stocks.entity.nse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    double strikePrice;
    String expiryDate;
    @JsonProperty("PE")
    PE pe;
    @JsonProperty("CE")
    CE ce;
}
