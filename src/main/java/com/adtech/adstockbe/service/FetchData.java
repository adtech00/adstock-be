package com.adtech.adstockbe.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class FetchData {

    public List<String> fetchData(String sName, String timeInterval, String timestamp) {
        String apiUrl = "https://api.upstox.com/historical/NSE_EQ/"
                +sName
                +"/"+timeInterval
                +"?timestamp="+timestamp;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.add("Authorization","Bearer 1f6c295fa47bb2da640a46978e1ba5b30caa4d1d5bf5ae71de1a35f10b970d50");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        ObjectMapper mapper = new ObjectMapper();
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
}
