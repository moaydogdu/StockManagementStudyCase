package com.study.stockmanagementstudycase.service.wareHouseStock;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.Stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface WareHouseStockUpdateService {
    WareHouseStock updateWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal entryAmount,
            final LocalDateTime entryTime
    );
}
