package com.study.stockmanagementstudycase.model.mappers.wareHouseStock;

import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;

public class WareHouseStockMapper {

    public static WareHouseStockEntity mapForStockEntry(
            final Stock stock
    ) {
        return WareHouseStockEntity.builder()
                .amount(stock.getAmount())
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
