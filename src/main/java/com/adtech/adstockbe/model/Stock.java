package com.adtech.adstockbe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity(name = "adstock_stock")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Stock {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column
    private String stockName;

    @Column
    private String details;

    @Column
    private List<UpstoxRes> upstoxResList;


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<UpstoxRes> getUpstoxResList() {
        return upstoxResList;
    }

    public void setUpstoxResList(List<UpstoxRes> upstoxResList) {
        this.upstoxResList = upstoxResList;
    }
}
