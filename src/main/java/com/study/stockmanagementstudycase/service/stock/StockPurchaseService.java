package com.study.stockmanagementstudycase.service.stock;

import com.study.stockmanagementstudycase.model.Stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StockPurchaseService {
    Stock purchaseStock(
            final String stockId,
            final String wareHouseId,
            final BigDecimal entryAmount,
            final LocalDateTime entryTime
    );
}
