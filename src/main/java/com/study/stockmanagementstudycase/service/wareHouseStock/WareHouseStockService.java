package com.study.stockmanagementstudycase.service.wareHouseStock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.aggregate.wareHouseStock.WareHouseStockAggregateWithWareHouse;

import java.util.List;

public interface WareHouseStockService {

    List<WareHouseStock> getWareHouseStocks();

    WareHouseStock getWareHouseStockById(
            final String wareHouseStockId
    );

    WareHouseStock getWareHouseStockByStockIdAndWareHouseId(
            final String stockId,
            final String wareHouseId
    );

    List<WareHouseStockAggregateWithWareHouse> getWareHouseStocksByStock(
            final Stock stock
    );
}
