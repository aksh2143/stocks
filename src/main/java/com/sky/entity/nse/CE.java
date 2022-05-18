package com.sky.entity.nse;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
public class CE {
    private static final DecimalFormat df = new DecimalFormat("0.0000");
    private static final DecimalFormat df2 = new DecimalFormat("0");

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
    double chipsPercent;
    double air;
    double airPercent;
    double distanceFromSpot;
    boolean isAtm;
    boolean isOtm;
    boolean isItm;
    String place;

    public void setChips(double chips) {
        chips = Double.parseDouble(df.format(chips));
        this.chips = chips;
    }

    public void setAir(double air) {
        air = Double.parseDouble(df.format(air));
        this.air = air;
    }

    public void setAirPercent(double airPercent) {
        if (Double.isFinite(airPercent))
            airPercent = Double.parseDouble(df2.format(airPercent));
        this.airPercent = airPercent;
    }
}
