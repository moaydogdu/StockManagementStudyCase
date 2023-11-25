package com.study.stockmanagementstudycase.model.mappers.stockTransaction;

import com.study.stockmanagementstudycase.model.StockTransaction;
import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import com.study.stockmanagementstudycase.model.enums.StockTransactionType;

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
            final Stock stock
    ) {
        return StockTransactionEntity.builder()
                .amount(entryAmount)
                .beforeAmount(stock.getAmount().subtract(entryAmount))
                .afterAmount(stock.getAmount())
                .date(entryTime)
                .stockTransactionType(StockTransactionType.STOCK_ENTRY)
                .build();
    }
}
