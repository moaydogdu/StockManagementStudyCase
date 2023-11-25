package com.study.stockmanagementstudycase.service.stockTransaction;

import com.study.stockmanagementstudycase.model.StockTransaction;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StockTransactionCreateService {
    StockTransaction createStockTransactionForStockCreate(
            final StockCreateRequest stockCreateRequest,
            final Stock stock
    );

    StockTransaction createStockTransactionForStockEntry(
            final BigDecimal entryAmount,
            final LocalDateTime entryTime,
            final Stock stock,
            final WareHouse wareHouse
    );
}
