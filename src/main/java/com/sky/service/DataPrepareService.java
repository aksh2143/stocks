package com.sky.service;

import com.sky.entity.nse.Instrument;
import com.sky.entity.nse.NSE;

public interface DataPrepareService {
    Instrument prepareInstrumentData(NSE nse, double round, double strikeRange, boolean current, String expiryDate) throws Exception;
}
