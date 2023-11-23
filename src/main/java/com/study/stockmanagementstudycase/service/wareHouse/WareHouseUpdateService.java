package com.study.stockmanagementstudycase.service.wareHouse;

import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;

public interface WareHouseUpdateService {
    void updateWareHouse(
            final WareHouseUpdateRequest updateRequest,
            final String id
    );
}
