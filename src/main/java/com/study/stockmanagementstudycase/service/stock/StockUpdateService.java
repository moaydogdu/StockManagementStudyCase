package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;

public interface StockUpdateService {
    void updateStock(
            final StockUpdateRequest updateRequest,
            final String stockId
    );
}
