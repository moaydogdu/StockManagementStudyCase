package com.study.stockmanagementstudycase.service.wareHouse;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.WareHouse;

import java.util.List;

public interface WareHouseService {

    CustomPage<WareHouse> getWareHouses(
            final CustomPagingRequest customPagingRequest
    );

    WareHouse getWareHouseById(final String wareHouseId);

}
