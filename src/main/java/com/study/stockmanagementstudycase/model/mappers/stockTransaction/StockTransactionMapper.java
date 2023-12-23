package com.study.stockmanagementstudycase.model.mappers.stockTransaction;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.StockTransaction;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import com.study.stockmanagementstudycase.model.enums.StockTransactionType;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class StockTransactionMapper {

    public static StockTransactionEntity mapForCreateStock(
            final StockCreateRequest stockCreateRequest
    ) {
        return StockTransactionEntity.builder()
                .amount(BigDecimal.ZERO)
                .beforeAmount(BigDecimal.ZERO)
                .afterAmount(BigDecimal.ZERO)
                .date(stockCreateRequest.getDateTime())
                .stockTransactionType(StockTransactionType.STOCK_CREATE)
                .build();
    }

    public static StockTransaction toDomainModel(
            final StockTransactionEntity stockTransactionEntity
    ) {
        return StockTransaction.builder()
                .id(stockTransactionEntity.getId())
                .amount(stockTransactionEntity.getAmount())
                .beforeAmount(stockTransactionEntity.getBeforeAmount())
                .afterAmount(stockTransactionEntity.getAfterAmount())
                .date(stockTransactionEntity.getDate())
                .stockTransactionType(stockTransactionEntity.getStockTransactionType())
                .createdAt(stockTransactionEntity.getCreatedAt())
                .updatedAt(stockTransactionEntity.getUpdatedAt())
                .build();
    }

    public static StockTransactionEntity mapForStockEntry(
            final BigDecimal entryAmount,
            final LocalDateTime entryTime,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        return StockTransactionEntity.builder()
                .amount(entryAmount)
                .beforeAmount(stock.getAmount().subtract(entryAmount))
                .afterAmount(stock.getAmount())
                .date(entryTime)
                .stockTransactionType(StockTransactionType.STOCK_ENTRY)
                .stockEntity(StockMapper.toEntity(stock))
                .wareHouseEntity(WareHouseMapper.toEntity(wareHouse))
                .build();
    }

    public static StockTransactionEntity mapForStockEntryForPastDate(
            final BigDecimal entryAmount,
            final LocalDateTime entryTime,
            final BigDecimal beforeAmount,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        return StockTransactionEntity.builder()
                .amount(entryAmount)
                .beforeAmount(beforeAmount)
                .afterAmount(beforeAmount.add(entryAmount))
                .date(entryTime)
                .stockTransactionType(StockTransactionType.STOCK_ENTRY)
                .stockEntity(StockMapper.toEntity(stock))
                .wareHouseEntity(WareHouseMapper.toEntity(wareHouse))
                .build();
    }

    public static StockTransactionEntity mapForStockSaleForPastDate(
            final BigDecimal saleAmount,
            final LocalDateTime saleTime,
            final BigDecimal beforeAmount,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        return StockTransactionEntity.builder()
                .amount(saleAmount)
                .beforeAmount(beforeAmount)
                .afterAmount(beforeAmount.subtract(saleAmount))
                .date(saleTime)
                .stockTransactionType(StockTransactionType.STOCK_SELL)
                .stockEntity(StockMapper.toEntity(stock))
                .wareHouseEntity(WareHouseMapper.toEntity(wareHouse))
                .build();
    }

    public static StockTransactionEntity mapForStockSale(
            final BigDecimal saleAmount,
            final LocalDateTime saleTime,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        return StockTransactionEntity.builder()
                .amount(saleAmount)
                .beforeAmount(stock.getAmount().add(saleAmount))
                .afterAmount(stock.getAmount())
                .date(saleTime)
                .stockTransactionType(StockTransactionType.STOCK_SELL)
                .stockEntity(StockMapper.toEntity(stock))
                .wareHouseEntity(WareHouseMapper.toEntity(wareHouse))
                .build();
    }
}
