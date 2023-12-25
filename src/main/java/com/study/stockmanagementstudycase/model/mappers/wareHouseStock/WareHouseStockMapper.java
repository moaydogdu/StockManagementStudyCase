package com.study.stockmanagementstudycase.model.mappers.wareHouseStock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.aggregate.wareHouseStock.WareHouseStockAggregateWithWareHouse;
import com.study.stockmanagementstudycase.model.dto.response.wareHouseStock.WareHouseStockResponse;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;

import java.math.BigDecimal;
import java.util.List;

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

    public static List<WareHouseStock> toDomainModel(
            final List<WareHouseStockEntity> wareHouseStockEntities
    ) {
        return wareHouseStockEntities.stream()
                .map(WareHouseStockMapper::toDomainModel)
                .toList();
    }

    public static WareHouseStockResponse toWareHouseStockResponse(
            final WareHouseStockAggregateWithWareHouse wareHouseStockAggregateWithWareHouse
    ) {
        return WareHouseStockResponse.builder()
                .wareHouseId(wareHouseStockAggregateWithWareHouse.getWareHouse().getId())
                .wareHouseName(wareHouseStockAggregateWithWareHouse.getWareHouse().getName())
                .amount(wareHouseStockAggregateWithWareHouse.getAmount())
                .build();
    }

    public static List<WareHouseStockResponse> toWareHouseStockResponse(
            final List<WareHouseStockAggregateWithWareHouse> wareHouseStockAggregateWithWareHouseList
    ) {
        return wareHouseStockAggregateWithWareHouseList.stream()
                .map(WareHouseStockMapper::toWareHouseStockResponse)
                .toList();
    }

    public static WareHouseStockAggregateWithWareHouse toAggregateWithWareHouse(
            final WareHouseStockEntity wareHouseStockEntity
    ) {
        return WareHouseStockAggregateWithWareHouse.builder()
                .id(wareHouseStockEntity.getId())
                .amount(wareHouseStockEntity.getAmount())
                .wareHouse(WareHouseMapper.toDomainModel(wareHouseStockEntity.getWareHouseEntity()))
                .build();

    }

    public static List<WareHouseStockAggregateWithWareHouse> toAggregateWithWareHouse(
            final List<WareHouseStockEntity> wareHouseStockEntities
    ) {
        return wareHouseStockEntities.stream()
                .map(WareHouseStockMapper::toAggregateWithWareHouse)
                .toList();
    }
}
