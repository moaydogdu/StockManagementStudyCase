package com.study.stockmanagementstudycase.service;

import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;

import java.util.List;

public interface WareHouseGetAllService {

    List<WareHouseResponse> getAllWareHouses();

}
