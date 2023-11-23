package com.study.stockmanagementstudycase.service.wareHouse;

import com.study.stockmanagementstudycase.model.WareHouse;

import java.util.List;

public interface WareHouseService {

    List<WareHouse> getWareHouses();

    WareHouse getWareHouseById(final String wareHouseId);

}