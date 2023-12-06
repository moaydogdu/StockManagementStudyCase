package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;

import java.util.List;

public interface StockService {
    List<Stock> getStocks();

    Stock getStockById(String stockId);

    void updateStock(String stockId, StockUpdateRequest stockUpdateRequest);

    void deleteStock(String stockId);

   ;
}
