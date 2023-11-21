package com.study.stockmanagementstudycase.model.mappers;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;

import java.util.Objects;

public class WareHouseMapper {

    public static WareHouseEntity mapForSaving(
            final WareHouseCreateRequest createRequest
    ) {
        return WareHouseEntity.builder()
                .name(createRequest.getName())
                .address(createRequest.getAddress())
                .build();
    }

    public static void mapForUpdating(
            final WareHouseUpdateRequest updateRequest,
            final WareHouseEntity wareHouseEntityFromDb
    ) {
        wareHouseEntityFromDb.setName(updateRequest.getName());
        wareHouseEntityFromDb.setAddress(updateRequest.getAddress());
    }

    public static WareHouse toDomainModel(
            final WareHouseEntity wareHouseEntity
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
