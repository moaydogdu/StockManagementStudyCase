package com.study.stockmanagementstudycase.service.wareHouseStock;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.Stock;

public interface WareHouseStockCreateService {
    WareHouseStock createWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse
    );
}
