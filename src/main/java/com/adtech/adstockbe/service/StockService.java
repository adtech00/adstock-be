package com.adtech.adstockbe.service;

import com.adtech.adstockbe.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StockService {
    
    public Stock save(Stock stock);

    public Stock create(Stock stock);

    public Stock getById(Long id);

    public Stock getByStockName(String stockName);

    public Stock getByStockSymbol(String stockSymbol);

    public List<Stock> getAll();

    public Stock update(Stock stock);
}
