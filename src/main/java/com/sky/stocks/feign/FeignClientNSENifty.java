package com.sky.stocks.feign;

import com.sky.stocks.entity.nse.NSE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name="nseservice", url = "https://www.nseindia.com")
@Component
public interface FeignClientNSENifty {
    @GetMapping("/api/option-chain-indices?symbol=NIFTY")
    NSE getLiveNiftyData(@RequestHeader Map<String, String> headerMap);
}
