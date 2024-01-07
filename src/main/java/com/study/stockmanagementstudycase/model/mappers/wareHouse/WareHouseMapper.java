package com.study.stockmanagementstudycase.model.mappers.wareHouse;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

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
        return WareHouse.builder()
                .id(wareHouseEntity.getId())
                .name(wareHouseEntity.getName())
                .address(wareHouseEntity.getAddress())
                .status(wareHouseEntity.getStatus())
                .build();
    }

    public static List<WareHouse> toDomainModel(
            final List<WareHouseEntity> wareHouseEntityList
    ) {
        return wareHouseEntityList.stream()
                .map(WareHouseMapper::toDomainModel)
                .toList();
    }

    public static List<WareHouse> toDomainModel(
            final Page<WareHouseEntity> wareHouseEntityPage
    ) {
        return wareHouseEntityPage.stream()
                .map(WareHouseMapper::toDomainModel)
                .toList();
    }

    public static WareHouseEntity toEntity(
            final WareHouse wareHouseDomainModel
    ) {
        return WareHouseEntity.builder()
                .id(wareHouseDomainModel.getId())
                .name(wareHouseDomainModel.getName())
                .address(wareHouseDomainModel.getAddress())
                .status(wareHouseDomainModel.getStatus())
                .createdAt(wareHouseDomainModel.getCreatedAt())
                .updatedAt(wareHouseDomainModel.getUpdatedAt())
                .build();
    }

}
