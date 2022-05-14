package com.sky.stocks.entity.nse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Strike {
    double strikePrice;
    CE ce;
    PE pe;
}
