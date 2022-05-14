package com.sky.stocks.service;

import com.sky.stocks.entity.nse.Instrument;
import com.sky.stocks.entity.nse.NSE;

public interface DataPrepareService {
    Instrument prepareInstrumentData(NSE nse, double round, double strikeRange, boolean current, String expiryDate) throws Exception;
}
