package com.study.stockmanagementstudycase.model.mappers.wareHouse;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.response.wareHouse.WareHouseResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            return Collections.emptyList();
        }

        return wareHouseList.stream()
                .map(WareHouseDTOMapper::toWareHouseResponse)
                .toList();
    }


    public static CustomPagingResponse<WareHouseResponse> toPagingResponse(
            final CustomPage<WareHouse> customPage
    ) {
        List<WareHouseResponse> resp = customPage.getContent().stream()
                .map(WareHouseDTOMapper::toWareHouseResponse)
                .toList();

        return CustomPagingResponse.<WareHouseResponse>builder()
                .content(resp)
                .of(customPage)
                .build();
    }
}
