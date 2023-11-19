package com.study.stockmanagementstudycase.model.mappers;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;

import java.util.List;
import java.util.Objects;

public class WareHouseDtoMapper {
    // TODO: her service uyesi icin ayri ayri methodlar yazilacak

    public static WareHouseResponse toGetResponse(
            WareHouse wareHouse
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

    public static List<WareHouseResponse> toGetResponse(
            List<WareHouse> wareHouseList
    ) {

        if (Objects.isNull(wareHouseList)) {
            return null;
        }
        return wareHouseList.stream()
                .map(WareHouseDtoMapper::toGetResponse)
                .toList();
    }
}
