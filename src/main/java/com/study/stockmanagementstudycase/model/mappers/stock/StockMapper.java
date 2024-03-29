package com.study.stockmanagementstudycase.model.mappers.stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public class StockMapper {

    public static StockEntity mapForSaving(
            final StockCreateRequest stockCreateRequest
    ) {
        return StockEntity.builder()
                .name(stockCreateRequest.getName())
                .price(stockCreateRequest.getPrice())
                .amount(BigDecimal.ZERO)
                .unitType(stockCreateRequest.getUnitType())
                .build();
    }

    public static Stock toDomainModel(
            final StockEntity stockEntity
    ) {
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .price(stockEntity.getPrice())
                .amount(stockEntity.getAmount())
                .unitType(stockEntity.getUnitType())
                .createdAt(stockEntity.getCreatedAt())
                .updatedAt(stockEntity.getUpdatedAt())
                .status(stockEntity.getStatus())
                .build();
    }
    public static List<Stock>  toDomainModel(
            final Page<StockEntity> stockEntity
    ) {
        return stockEntity.stream()
                .map(StockMapper::toDomainModel)
                .toList();
    }

    public static StockEntity toEntity(
            final Stock stockDomainModel
    ) {
        return StockEntity.builder()
                .id(stockDomainModel.getId())
                .name(stockDomainModel.getName())
                .price(stockDomainModel.getPrice())
                .amount(stockDomainModel.getAmount())
                .unitType(stockDomainModel.getUnitType())
                .createdAt(stockDomainModel.getCreatedAt())
                .updatedAt(stockDomainModel.getUpdatedAt())
                .build();
    }

    public static void mapForUpdating(
            final StockUpdateRequest updateRequest,
            final StockEntity stockEntityFromDb
    ) {
        stockEntityFromDb.setName(updateRequest.getName());
        stockEntityFromDb.setPrice(updateRequest.getPrice());
    }
}
