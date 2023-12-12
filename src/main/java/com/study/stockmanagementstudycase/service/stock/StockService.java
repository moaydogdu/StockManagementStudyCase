package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getStocks();

    Stock getStockById(String stockId);
}
