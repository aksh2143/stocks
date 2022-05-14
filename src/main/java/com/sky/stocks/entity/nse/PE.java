package com.sky.stocks.entity.nse;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
public class PE {
    private static final DecimalFormat df = new DecimalFormat("0.0000");

    double strikePrice;

    String expiryDate;

    String underlying;

    String identifier;

    double openInterest;

    double changeinOpenInterest;

    double pchangeinOpenInterest;

    double totalTradedVolume;

    double impliedVolatility;

    double lastPrice;

    double change;

    double pChange;

    double totalBuyQuantity;

    double totalSellQuantity;

    double bidQty;

    double bidprice;

    double askQty;

    double askPrice;

    double underlyingValue;

    double totOI;

    double totVol;
    public void setChange(double change) {
        change = Double.parseDouble(df.format(change));
        this.change = change;
    }

    double chips;

    double air;
    double distanceFromSpot;

    public void setChips(double chips) {
        chips = Double.parseDouble(df.format(chips));
        this.chips = chips;
    }

    public void setAir(double air) {
        air = Double.parseDouble(df.format(air));
        this.air = air;
    }
}
