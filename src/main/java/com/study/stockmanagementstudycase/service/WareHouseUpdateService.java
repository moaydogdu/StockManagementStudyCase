package com.study.stockmanagementstudycase.service;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseUpdateRequest;

public interface WareHouseUpdateService {
    void updateWareHouse(WareHouseUpdateRequest updateRequest, String id);
}
