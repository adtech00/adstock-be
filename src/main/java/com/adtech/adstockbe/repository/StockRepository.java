package com.adtech.adstockbe.repository;

import com.adtech.adstockbe.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface StockRepository extends JpaRepository<Stock, Serializable> {
    public Stock findById(Long id);

    public Stock findByStockSymbol(String symbol);

    public Stock findByStockName(String symbol);
}
