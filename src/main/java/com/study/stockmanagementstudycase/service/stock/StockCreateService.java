package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;

public interface StockCreateService {
    Stock createStock(
            final StockCreateRequest stockCreateRequest
    );
}
