package com.study.stockmanagementstudycase.model.mappers.wareHouse;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;
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

    public static WareHouseEntity toEntity(
            final WareHouse wareHouseDomainModel
    ) {
        return WareHouseEntity.builder()
                .id(wareHouseDomainModel.getId())
                .name(wareHouseDomainModel.getName())
                .address(wareHouseDomainModel.getAddress())
                .createdAt(wareHouseDomainModel.getCreatedAt())
                .updatedAt(wareHouseDomainModel.getUpdatedAt())
                .build();
    }

}