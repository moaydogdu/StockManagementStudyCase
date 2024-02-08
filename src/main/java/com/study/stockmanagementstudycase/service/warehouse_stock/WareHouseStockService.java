package com.study.stockmanagementstudycase.service.warehouse_stock;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
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

    CustomPage<WareHouseStockAggregateWithWareHouse> getWareHouseStocksByStock(
            final Stock stock,
            final CustomPagingRequest customPagingRequest
    );
}
