package com.adtech.adstockbe.webservice;

import com.adtech.adstockbe.service.StockService;
import com.adtech.adstockbe.service.responseservice.ResponseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("stock")
public class StockResource {

    @Autowired
    private ResponseMessageService responseMessageService;

    @Autowired
    private StockService stockService;

    //@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAllStocks(Principal principal) {
            return ResponseEntity
                    .ok(responseMessageService
                            .generateResponseMessage(stockService.getAll(), null, HttpStatus.OK));
    }
}
