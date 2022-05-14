package com.sky;

import com.sky.entity.nse.ApplicationStaticData;
import com.sky.utils.URLS;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@SpringBootApplication
@EnableFeignClients
public class StocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
