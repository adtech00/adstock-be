package com.adtech.adstockbe.Test;

import com.adtech.adstockbe.model.Stock;
import com.adtech.adstockbe.service.StockService;
import com.adtech.adstockbe.service.externalapis.UpstoxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    private UpstoxDataService upstoxDataService;

    @Autowired
    private StockService stockService;

    public Stock fetchStockHistoricalData(String stockSymbol, String timeInterval, String timestamp) {
        return upstoxDataService.fetchStockHistoricalData(stockSymbol, stockSymbol, "This Stock Traded as "+stockSymbol+" in National Stock Exchange of India", timeInterval, timestamp);
    }

    public Stock autoPopulate(String stockSymbol, String timeInterval, String timestamp) {
        Stock stock = fetchStockHistoricalData(stockSymbol, timeInterval, timestamp);
        return stockService.save(stock);
    }
}
