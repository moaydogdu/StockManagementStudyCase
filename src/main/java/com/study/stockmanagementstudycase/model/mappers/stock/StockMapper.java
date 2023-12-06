package com.study.stockmanagementstudycase.model.mappers.stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.dto.response.StockGetResponse;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseDTOMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StockMapper {

    public static StockEntity mapForCreating(
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
                .build();
    }
    public static List<Stock> toDomainModel(
            List<StockEntity> stockEntities
    ){
        return stockEntities.stream().map(StockMapper::toDomainModel).toList();
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
    public static StockGetResponse toStockResponse(
            final Stock stock
    ){
        return StockGetResponse.builder()
                .name(stock.getName())
                .price(stock.getPrice())
                .amount(stock.getAmount())
                .unitType(stock.getUnitType())
                .build();


    }
    public static List<StockGetResponse> toStockResponse(
            final List<Stock> stocks
    ) {
        if (Objects.isNull(stocks)) {
            return null;
        }

        return stocks.stream()
                .map(StockMapper::toStockResponse)
                .toList();
    }

    public static Stock UpdateforDomainModel(StockEntity stockEntity, StockUpdateRequest stockUpdateRequest) {
        stockEntity.setName(stockUpdateRequest.getName());
        stockEntity.setAmount(stockUpdateRequest.getAmount());
        stockEntity.setPrice(stockUpdateRequest.getPrice());
        stockEntity.setUnitType(stockUpdateRequest.getUnitType());
        stockEntity.setUpdatedAt(LocalDateTime.now());
        return StockMapper.toDomainModel(stockEntity);
    }
}
