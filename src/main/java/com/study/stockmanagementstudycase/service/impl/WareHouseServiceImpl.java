package com.study.stockmanagementstudycase.service.impl;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WareHouseServiceImpl implements WareHouseService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public List<WareHouse> getWareHouses() {

        List<WareHouseEntity> wareHouseEntityList = wareHouseRepository.findAll();

        return wareHouseEntityList.stream()
                .map(WareHouseMapper::toWareHouse)
                .toList();
    }
    @Override
    public WareHouse getWareHouseById(final String wareHouseId) {
        Optional<WareHouseEntity> wareHouseEntity = wareHouseRepository.findById(wareHouseId);
        WareHouseEntity wareHouseEntity1 = wareHouseEntity.orElseThrow(()->new RuntimeException("WareHouse cant find given id"));
            return WareHouseMapper.toWareHouse(wareHouseEntity1);


}}
