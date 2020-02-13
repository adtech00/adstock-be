package com.adtech.adstockbe.webservice;

import com.adtech.adstockbe.Test.DataService;
import com.adtech.adstockbe.service.responseservice.ResponseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestResource {

    @Autowired
    private ResponseMessageService responseMessageService;

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/print/{value}", method = RequestMethod.GET)
    public ResponseEntity printValue(@PathVariable("value") String value){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(value, null, HttpStatus.OK));
    }

    @RequestMapping(value = "/historical/{stockSymbol}", method = RequestMethod.GET)
    public ResponseEntity getHistoricalStockData(@PathVariable("stockSymbol") String stockSymbol, @RequestParam(required = false, value = "interval", defaultValue = "1") String interval, @RequestParam(required = false, value = "timestamp", defaultValue = "") String timestamp){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(dataService.fetchStockHistoricalData(stockSymbol, interval, timestamp), null, HttpStatus.OK));
    }

    @RequestMapping(value = "/populate/auto/{stockSymbol}", method = RequestMethod.PUT)
    public ResponseEntity autoPopulate(@PathVariable("stockSymbol") String stockSymbol, @RequestParam(required = false, value = "interval", defaultValue = "1") String interval, @RequestParam(required = false, value = "timestamp", defaultValue = "") String timestamp){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(dataService.autoPopulate(stockSymbol, interval, timestamp), null, HttpStatus.OK));
    }
}
