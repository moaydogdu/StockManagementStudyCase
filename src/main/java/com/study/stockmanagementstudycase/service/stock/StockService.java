package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockPagingRequest;

public interface StockService {
    CustomPage<Stock> getStocks(
            final CustomPagingRequest customPagingRequest
    );

    Stock getStockById(String stockId);

    CustomPage<Stock> getDeletedStocks(
            final StockPagingRequest stockPagingRequest
    );
}
