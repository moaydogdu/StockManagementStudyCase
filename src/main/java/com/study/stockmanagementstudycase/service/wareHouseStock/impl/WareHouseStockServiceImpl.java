package com.study.stockmanagementstudycase.service.wareHouseStock.impl;

import com.study.stockmanagementstudycase.common.exception.WareHouseStockNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WareHouseStockServiceImpl implements WareHouseStockService {

    private final WareHouseStockRepository wareHouseStockRepository;

    @Override
    public List<WareHouseStock> getWareHouseStocks() {

        final List<WareHouseStockEntity> wareHouseStockEntityList = wareHouseStockRepository
                .findAll();

        if (Boolean.TRUE.equals(wareHouseStockEntityList.isEmpty())) {
            throw new WareHouseStockNotFoundException();
        }

        return wareHouseStockEntityList.stream()
                .map(WareHouseStockMapper::toDomainModel)
                .toList();
    }

    @Override
    public WareHouseStock getWareHouseStockById(
            final String wareHouseStockId
    ) {
        final WareHouseStockEntity wareHouseStockEntityFromDb = wareHouseStockRepository
                .findById(wareHouseStockId)
                .orElseThrow(WareHouseStockNotFoundException::new);

        return WareHouseStockMapper
                .toDomainModel(wareHouseStockEntityFromDb);
    }
}
