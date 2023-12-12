package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;

public interface StockCreateService {
    Stock createStock(
            final StockCreateRequest stockCreateRequest
    );
}
