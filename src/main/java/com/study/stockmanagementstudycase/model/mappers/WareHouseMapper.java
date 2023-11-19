package com.study.stockmanagementstudycase.model.mappers;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;

import java.util.Objects;

// Dto ve Entity arasindaki donusumu yapan mapperdir
public class WareHouseMapper {


    public static WareHouseEntity mapForSaving(
            WareHouseCreateRequest request
    ) {
        return WareHouseEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static WareHouse toWareHouse(
            WareHouseEntity wareHouseEntity
    ) {
        if (Objects.isNull(wareHouseEntity)) {
            return null;
        }
        return WareHouse.builder()
                .id(wareHouseEntity.getId())
                .name(wareHouseEntity.getName())
                .address(wareHouseEntity.getAddress())
                .build();
    }

}
