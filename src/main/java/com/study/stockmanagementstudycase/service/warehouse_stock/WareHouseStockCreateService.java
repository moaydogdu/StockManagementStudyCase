package com.study.stockmanagementstudycase.service.warehouse_stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;

import java.math.BigDecimal;

public interface WareHouseStockCreateService {
    WareHouseStock createWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal entryAmount
    );
}
