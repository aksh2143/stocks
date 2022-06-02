package com.sky;

import com.sky.entity.nse.ApplicationStaticData;
import com.sky.entity.nse.NSE;
import com.sky.feign.FeignBuilder;
import com.sky.feign.FeignClientNSEBankNifty;
import com.sky.feign.FeignClientNSENifty;
import com.sky.utils.URLS;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@EnableFeignClients
public class StocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

    @Autowired
    FeignClientNSENifty feignClientNSENifty;

    @Autowired
    FeignClientNSEBankNifty feignClientNSEBankNifty;


    @Bean
    public void loadInitData() {
        try {

            URL fnoListUrl = new URL(URLS.GET_FNO_STOCKS);
            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
            HashMap<String, String> fnoList = new HashMap<>();

            try (CSVParser csvParser = CSVParser.parse(fnoListUrl, StandardCharsets.UTF_8, csvFormat)) {
                for (CSVRecord record : csvParser) {
                    String stockSymbol = record.get(1);
                    String stockName = record.get(0);
                    fnoList.put(stockSymbol, stockName);
                }
            }
            ApplicationStaticData.fnoStockList = fnoList;
            ApplicationStaticData.headersMap = FeignBuilder.builder();

            /**
             * Nifty
             */
            NSE nse = feignClientNSENifty.getLiveNiftyData(FeignBuilder.builder());
            ApplicationStaticData.niftyExpiryDates = Arrays.asList(nse.getRecords().getExpiryDates());

            Map<String, String> hm = new LinkedHashMap<>();
            ApplicationStaticData.niftyExpiryDates.stream().forEach(expiry -> hm.put(expiry, expiry));
            ApplicationStaticData.niftyExpiryDatesMap = hm;

            /**
             * Bank Nifty
             */
            NSE bnfNse = feignClientNSEBankNifty.getLiveBankNiftyData(FeignBuilder.builder());
            ApplicationStaticData.bnfExpiryDates = Arrays.asList(bnfNse.getRecords().getExpiryDates());

            Map<String, String> hmbnf = new LinkedHashMap<>();
            ApplicationStaticData.bnfExpiryDates.stream().forEach(expiry -> hmbnf.put(expiry, expiry));
            ApplicationStaticData.bnfExpiryDatesMap = hmbnf;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
