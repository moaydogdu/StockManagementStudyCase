package com.study.stockmanagementstudycase.service.wareHouse.impl;

import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WareHouseServiceImpl implements WareHouseService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public List<WareHouse> getWareHouses() {
        final List<WareHouseEntity> wareHouseEntityList = wareHouseRepository
                .findAll();

        return wareHouseEntityList.stream()
                .map(WareHouseMapper::toDomainModel)
                .toList();
    }

    @Override
    public WareHouse getWareHouseById(
            final String wareHouseId
    ) {
        final WareHouseEntity wareHouseEntityFromDb = wareHouseRepository
                .findById(wareHouseId)
                .orElseThrow(() -> new WareHouseNotFoundException("WareHouse can not find given id"));

        return WareHouseMapper
                .toDomainModel(wareHouseEntityFromDb);
    }

}
