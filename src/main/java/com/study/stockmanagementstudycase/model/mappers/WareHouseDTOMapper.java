package com.study.stockmanagementstudycase.model.mappers;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;

import java.util.List;
import java.util.Objects;

public class WareHouseDTOMapper {

    public static WareHouseResponse toWareHouseResponse(
            final WareHouse wareHouse
    ) {
        if (Objects.isNull(wareHouse)) {
            return null;
        }

        return WareHouseResponse.builder()
                .id(wareHouse.getId())
                .name(wareHouse.getName())
                .address(wareHouse.getAddress())
                .build();
    }

    public static List<WareHouseResponse> toWareHouseResponse(
            final List<WareHouse> wareHouseList
    ) {
        if (Objects.isNull(wareHouseList)) {
            return null;
        }

        return wareHouseList.stream()
                .map(WareHouseDTOMapper::toWareHouseResponse)
                .toList();
    }
}
