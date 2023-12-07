package com.study.stockmanagementstudycase.model.mappers.wareHouseStock;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;

import java.math.BigDecimal;

public class WareHouseStockMapper {

    public static WareHouseStockEntity mapForSaving(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal entryAmount
    ) {
        return WareHouseStockEntity.builder()
                .stockEntity(
                        StockMapper.toEntity(stock)
                )
                .wareHouseEntity(
                        WareHouseMapper.toEntity(wareHouse)
                )
                .amount(entryAmount)
                .build();
    }

    public static WareHouseStock toDomainModel(
            final WareHouseStockEntity wareHouseStockEntity
    ) {
        return WareHouseStock.builder()
                .id(wareHouseStockEntity.getId())
                .amount(wareHouseStockEntity.getAmount())
                .stockEntityId(wareHouseStockEntity.getStockEntity().getId())
                .wareHouseEntityId(wareHouseStockEntity.getWareHouseEntity().getId())
                .createdAt(wareHouseStockEntity.getCreatedAt())
                .updatedAt(wareHouseStockEntity.getUpdatedAt())
                .build();
    }
}
