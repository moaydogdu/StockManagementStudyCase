package com.study.stockmanagementstudycase.model.mappers.wareHouse;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.response.wareHouse.WareHouseResponse;
import com.study.stockmanagementstudycase.model.dto.response.wareHouse.WareHouseResponseWithStatus;

import java.util.Collections;
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
            return Collections.emptyList();
        }

        return wareHouseList.stream()
                .map(WareHouseDTOMapper::toWareHouseResponse)
                .toList();
    }

    public static WareHouseResponseWithStatus toWareHouseResponseWithStatus(
            final WareHouse wareHouse
    ) {
        return WareHouseResponseWithStatus.builder()
                .id(wareHouse.getId())
                .name(wareHouse.getName())
                .address(wareHouse.getAddress())
                .status(wareHouse.getStatus())
                .build();
    }

    public static List<WareHouseResponseWithStatus> toWareHouseResponseWithStatus(
            final List<WareHouse> wareHouses
    ) {
        return wareHouses.stream()
                .map(WareHouseDTOMapper::toWareHouseResponseWithStatus)
                .toList();
    }


    public static CustomPagingResponse<WareHouseResponse> toPagingResponseWithWareHouseResponse(
            final CustomPage<WareHouse> customPage
    ) {
        return CustomPagingResponse.<WareHouseResponse>builder()
                .of(customPage)
                .content(
                        customPage.getContent() == null ? null :
                                customPage.getContent().stream().map(WareHouseDTOMapper::toWareHouseResponse).toList()
                )
                .build();
    }

    public static CustomPagingResponse<WareHouseResponseWithStatus> toPagingResponseWithWareHouseResponseWithStatus(
            final CustomPage<WareHouse> wareHouseCustomPage
    ) {
        return CustomPagingResponse.<WareHouseResponseWithStatus>builder()
                .of(wareHouseCustomPage)
                .content(
                        wareHouseCustomPage.getContent() == null ? null :
                                wareHouseCustomPage.getContent().stream()
                                        .map(WareHouseDTOMapper::toWareHouseResponseWithStatus)
                                        .toList()
                )
                .build();
    }
}
