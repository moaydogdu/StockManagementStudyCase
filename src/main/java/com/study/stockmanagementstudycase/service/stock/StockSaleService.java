package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.dto.request.stock.StockSaleRequest;

public interface StockSaleService {
    void saleStockById(
            final String stockId,
            final StockSaleRequest stockSaleRequest
    );
}
