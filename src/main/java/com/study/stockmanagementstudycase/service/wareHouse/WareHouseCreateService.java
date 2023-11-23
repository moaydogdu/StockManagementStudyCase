package com.study.stockmanagementstudycase.service.wareHouse;

import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;

public interface WareHouseCreateService {
    void createWareHouse(
            final WareHouseCreateRequest request
    );
}
