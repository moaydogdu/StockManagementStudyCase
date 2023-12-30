package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.Stock;

public interface StockService {
    CustomPage<Stock> getStocks(
            final CustomPagingRequest customPagingRequest
    );

    Stock getStockById(String stockId);
}
