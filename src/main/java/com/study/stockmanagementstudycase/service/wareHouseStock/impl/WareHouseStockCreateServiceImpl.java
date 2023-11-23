package com.study.stockmanagementstudycase.service.wareHouseStock.impl;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WareHouseStockCreateServiceImpl implements WareHouseStockCreateService {

    private final WareHouseStockRepository wareHouseStockRepository;

    @Override
    public WareHouseStock createWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse
    ) {
        final WareHouseStockEntity wareHouseStockEntityForStockEntry = WareHouseStockMapper
                .mapForStockEntry(stock);

        wareHouseStockEntityForStockEntry.setStockEntity(
                StockMapper.toEntity(stock)
        );

        wareHouseStockEntityForStockEntry.setWareHouseEntity(
                WareHouseMapper.toEntity(wareHouse)
        );

        wareHouseStockRepository.save(wareHouseStockEntityForStockEntry);

        return WareHouseStockMapper.toDomainModel(wareHouseStockEntityForStockEntry);
    }
}
