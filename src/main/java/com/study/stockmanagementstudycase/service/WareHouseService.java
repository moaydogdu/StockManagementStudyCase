package com.study.stockmanagementstudycase.service;

import com.study.stockmanagementstudycase.model.WareHouse;

import java.util.List;

public interface WareHouseService {

    List<WareHouse> getWareHouses();

    void deleteWareHouse(String id);

}
