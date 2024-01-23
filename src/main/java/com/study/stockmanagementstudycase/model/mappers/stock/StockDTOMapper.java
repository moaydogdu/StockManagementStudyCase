package com.study.stockmanagementstudycase.model.mappers.stock;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.response.stock.StockResponse;
import com.study.stockmanagementstudycase.model.dto.response.stock.StockResponseWithStatus;

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
            final List<Stock> stockDomainModels
    ) {
        return stockDomainModels.stream()
                .map(StockDTOMapper::toStockResponse)
                .toList();
    }

    public static StockResponseWithStatus toStockResponseWithStatus(
            final Stock stock
    ) {
        return StockResponseWithStatus.builder()
                .name(stock.getName())
                .amount(stock.getAmount())
                .price(stock.getPrice())
                .name(stock.getName())
                .unitType(stock.getUnitType())
                .status(stock.getStatus())
                .build();
    }

    public static List<StockResponseWithStatus> toStockResponseWithStatus(
            final List<Stock> stockDomainModels
    ) {
        return stockDomainModels.stream()
                .map(StockDTOMapper::toStockResponseWithStatus)
                .toList();
    }

    public static CustomPagingResponse<StockResponse> toPagingResponseWithStockResponse(
            final CustomPage<Stock> customPage
    ) {
        return CustomPagingResponse.<StockResponse>builder()
                .of(customPage)
                .content(
                        customPage.getContent() == null ? null :
                                customPage.getContent().stream()
                                  .map(StockDTOMapper::toStockResponse)
                                  .toList()
                )
                .build();
    }

    public static CustomPagingResponse<StockResponseWithStatus> toPagingResponseWithStockResponseWithStatus(
            final CustomPage<Stock> stockCustomPage
    ) {
        return CustomPagingResponse.<StockResponseWithStatus>builder()
                .of(stockCustomPage)
                .content(
                        stockCustomPage.getContent() == null ? null :
                                stockCustomPage.getContent().stream()
                                        .map(StockDTOMapper::toStockResponseWithStatus)
                                        .toList()
                )
                .build();
    }
}

