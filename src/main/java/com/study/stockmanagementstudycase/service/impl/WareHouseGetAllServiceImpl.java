package com.study.stockmanagementstudycase.service.impl;

import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.WareHouseDtoConverter;
import com.study.stockmanagementstudycase.model.mappers.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.WareHouseGetAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WareHouseGetAllServiceImpl implements WareHouseGetAllService {

    private final WareHouseRepository wareHouseRepository;
    private final WareHouseDtoConverter wareHouseDtoConverter;

    @Override
    public List<WareHouseResponse> getAllWareHouses() {

        List<WareHouseEntity> wareHouseEntityList = wareHouseRepository.findAll();

        return wareHouseEntityList.stream()
                .map(wareHouseDtoConverter::getConvertWareHouse)
                .collect(Collectors.toList());
    }

}
