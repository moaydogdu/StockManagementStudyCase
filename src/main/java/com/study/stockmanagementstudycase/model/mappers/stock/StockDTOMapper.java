package com.study.stockmanagementstudycase.model.mappers.stock;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.response.stock.StockResponse;

import java.util.List;

public class StockDTOMapper {
    public static StockResponse toStockResponse(
            final Stock stock
    ) {
        return StockResponse.builder()
                .id(stock.getId())
                .amount(stock.getAmount())
                .price(stock.getPrice())
                .name(stock.getName())
                .unitType(stock.getUnitType())
                .build();
    }

    public static List<StockResponse> toStockResponse(
            final List<Stock> stockList
    ) {
        return stockList
                .stream()
                .map(StockDTOMapper::toStockResponse)
                .toList();
    }

    public static CustomPagingResponse<StockResponse> toPagingResponse(
            final CustomPage<Stock> customPage
    ) {
        return CustomPagingResponse.<StockResponse>builder()
                .of(customPage)
                .content(
                        customPage.getContent() == null ? null :
                                customPage.getContent().stream().map(StockDTOMapper::toStockResponse).toList()
                )
                .build();
    }
}

