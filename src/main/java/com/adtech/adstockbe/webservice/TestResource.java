package com.adtech.adstockbe.webservice;

import com.adtech.adstockbe.service.responseservice.ResponseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestResource {

    @Autowired
    private ResponseMessageService responseMessageService;

    @RequestMapping(value = "/print/{value}", method = RequestMethod.GET)
    public ResponseEntity printValue(@PathVariable("value") String value){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(value, null, HttpStatus.OK));
    }
}
