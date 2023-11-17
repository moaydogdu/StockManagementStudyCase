package com.study.stockmanagementstudycase.model.mappers;

import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import org.springframework.stereotype.Component;

@Component
public class WareHouseDtoConverter {

    public WareHouseResponse getConvertWareHouse(WareHouseEntity wareHouse) {
        return WareHouseResponse.builder()
                .name(wareHouse.getName())
                .address(wareHouse.getAddress())
                .build();
    }

}
