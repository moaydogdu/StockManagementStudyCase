package com.study.stockmanagementstudycase.service.wareHouse;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;

public interface WareHouseCreateService {
    WareHouse createWareHouse(
            final WareHouseCreateRequest request
    );
}
