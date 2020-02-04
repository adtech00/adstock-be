package com.adtech.adstockbe.service.externalapis;

import com.adtech.adstockbe.model.HistoricalData;
import com.adtech.adstockbe.model.Stock;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UpstoxDataService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    RestTemplate restTemplate;


    public List<String> fetchHistoricalData(String sName, String timeInterval, String timestamp) {
        String apiUrl = "https://api.upstox.com/historical/NSE_EQ/"
                +sName
                +"/"+timeInterval
                +"?timestamp="+timestamp;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.add("Authorization","Bearer 1f6c295fa47bb2da640a46978e1ba5b30caa4d1d5bf5ae71de1a35f10b970d50");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            //List<String> upstoxResList = mapper.readValue(jsonObject.get("data").toString(), type);
            return mapper.readValue(jsonObject.get("data").toString(), type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Stock fetchStockHistoricalData(String stockSymbol, String stockName, String stockDescription, String timeInterval, String timestamp) {
        String apiUrl = "https://api.upstox.com/historical/NSE_EQ/"
                +stockSymbol
                +"/"+timeInterval
                +"?timestamp="+timestamp;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.add("Authorization","Bearer 1f6c295fa47bb2da640a46978e1ba5b30caa4d1d5bf5ae71de1a35f10b970d50");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<HistoricalData>historicalDataList=new ArrayList<>();
        try {
            List<String> stringList = mapper.readValue(jsonObject.get("data").toString(), type);
            stringList.forEach(x->{
                HistoricalData historicalData =new HistoricalData();
                historicalData.setTimestamp(Long.valueOf(x.split(",")[0].trim()));
                historicalData.setTime(new Timestamp(Long.valueOf(x.split(",")[0].trim())).toString());
                historicalData.setOpenPrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[1].trim())));
                historicalData.setHighPrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[2].trim())));
                historicalData.setLowPrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[3].trim())));
                historicalData.setClosePrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[4].trim())));
                historicalData.setVolume(Integer.valueOf(x.split(",")[5].replace(".0","").trim()));

                historicalDataList.add(historicalData);
            });

            Stock stock=new Stock();
            stock.setStockSymbol(stockSymbol);
            stock.setStockName(stockName);
            stock.setDetails(stockDescription);
            stock.setHistoricalDataList(historicalDataList);

            return stock;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
