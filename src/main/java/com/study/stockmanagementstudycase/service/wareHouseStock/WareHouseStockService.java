package com.study.stockmanagementstudycase.service.wareHouseStock;

import com.study.stockmanagementstudycase.model.WareHouseStock;

import java.util.List;

public interface WareHouseStockService {

    List<WareHouseStock> getWareHouseStocks();

    WareHouseStock getWareHouseStockById(final String wareHouseStockId);

}
