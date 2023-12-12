package com.study.stockmanagementstudycase.model.mappers.stock;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.response.StockResponse;

import java.util.List;

public class StockDTOMapper {
    public static StockResponse toStockResponse(
            final Stock stock
    ) {
        return StockResponse.builder()
                .amount(stock.getAmount())
                .price(stock.getPrice())
                .name(stock.getName())
                .unitType(stock.getUnitType())
                .build();
    }

    public static List<StockResponse> stockResponses(
            final List<Stock> stockList
    ) {
        return stockList
                .stream()
                .map(StockDTOMapper::toStockResponse)
                .toList();
    }
}

