package com.study.stockmanagementstudycase.service;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;

public interface WareHouseCreateService {
    WareHouseEntity createWareHouse(WareHouseCreateRequest request);
}
