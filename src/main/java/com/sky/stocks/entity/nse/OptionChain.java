package com.sky.stocks.entity.nse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OptionChain {
    String expiryDate;
    public List<Strike> strikeList;
}
