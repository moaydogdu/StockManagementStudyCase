package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockEntryRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StockEntryService {
    Stock entryStock(
            final String stockId,
            final String wareHouseId,
            final BigDecimal entryAmount,
            final LocalDateTime entryTime
    );

    Stock entryStock(
            final String stockId,
            final StockEntryRequest stockEntryRequest
    );
}
