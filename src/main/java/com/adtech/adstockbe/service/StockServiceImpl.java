package com.adtech.adstockbe.service;

import com.adtech.adstockbe.model.Stock;
import com.adtech.adstockbe.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock getById(Long id) {
        return stockRepository.findByStockId(id);
    }

    @Override
    public Stock getByStockName(String stockName) {
        return stockRepository.findByStockName(stockName);
    }

    @Override
    public Stock getByStockSymbol(String stockSymbol) {
        return stockRepository.findByStockSymbol(stockSymbol);
    }

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock update(Stock stock) {
        return stockRepository.save(stock);
    }
}
