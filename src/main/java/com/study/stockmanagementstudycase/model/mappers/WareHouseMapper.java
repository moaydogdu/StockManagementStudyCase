package com.study.stockmanagementstudycase.model.mappers;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;

public class WareHouseMapper {

    public static WareHouseEntity mapForSaving(
            WareHouseCreateRequest request
    ) {
        return WareHouseEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
