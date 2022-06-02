package com.sky.feign;

import com.sky.entity.nse.NSE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name="nseservice1", url = "https://www.nseindia.com")
@Component
public interface FeignClientNSEBankNifty {
    @GetMapping("/api/option-chain-indices?symbol=BANKNIFTY")
    NSE getLiveBankNiftyData(@RequestHeader Map<String, String> headerMap);
}
