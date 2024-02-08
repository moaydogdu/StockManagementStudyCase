package com.study.stockmanagementstudycase.service.warehouse_stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface WareHouseStockUpdateService {
    WareHouseStock updateWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal entryAmount,
            final LocalDateTime entryTime
    );

    WareHouseStock updateWareHouseStockForStockSale(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal saleAmount,
            final LocalDateTime saleTime
    );
}
