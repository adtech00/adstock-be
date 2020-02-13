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

    @Column(unique = true)
    private String stockSymbol;

    @Column(unique = true)
    private String stockName;

    @Column
    private String details;

    @Transient
    private List<HistoricalData> historicalDataList;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

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

    public List<HistoricalData> getHistoricalDataList() {
        return historicalDataList;
    }

    public void setHistoricalDataList(List<HistoricalData> historicalDataList) {
        this.historicalDataList = historicalDataList;
    }
}
