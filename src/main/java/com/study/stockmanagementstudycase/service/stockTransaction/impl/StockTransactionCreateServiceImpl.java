package com.study.stockmanagementstudycase.service.stockTransaction.impl;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.StockTransaction;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.stockTransaction.StockTransactionMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.StockTransactionRepository;
import com.study.stockmanagementstudycase.service.stockTransaction.StockTransactionCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockTransactionCreateServiceImpl implements StockTransactionCreateService {

    private final StockTransactionRepository stockTransactionRepository;

    @Override
    public StockTransaction createStockTransactionForStockCreate(
            final StockCreateRequest stockCreateRequest,
            final Stock stock
    ) {
        final StockTransactionEntity stockTransactionEntityToBeSaveForStockCreate =
                StockTransactionMapper.mapForCreateStock(stockCreateRequest);

        stockTransactionEntityToBeSaveForStockCreate.setStockEntity(
                StockMapper.toEntity(stock)
        );

        stockTransactionRepository.save(
                stockTransactionEntityToBeSaveForStockCreate
        );

        return StockTransactionMapper
                .toDomainModel(stockTransactionEntityToBeSaveForStockCreate);
    }

    @Override
    public StockTransaction createStockTransactionForStockEntry(
            final BigDecimal entryAmount,
            final LocalDateTime entryTime,
            final Stock stock,
            final WareHouse wareHouse
    ) {

        if (this.checkStockEntryTimeComparingToNow(entryTime)) {
            return createStockTransactionForStockEntryForPastDate(
                    entryAmount,
                    entryTime,
                    stock,
                    wareHouse
            );
        }

        final StockTransactionEntity stockTransactionEntityToBeSaveForStockEntry =
                StockTransactionMapper.mapForStockEntry(
                        entryAmount,
                        entryTime,
                        stock,
                        wareHouse
                );

        stockTransactionEntityToBeSaveForStockEntry.setStockEntity(
                StockMapper.toEntity(stock)
        );

        stockTransactionEntityToBeSaveForStockEntry.setWareHouseEntity(
                WareHouseMapper.toEntity(wareHouse)
        );

        stockTransactionRepository.save(
                stockTransactionEntityToBeSaveForStockEntry
        );

        return StockTransactionMapper
                .toDomainModel(stockTransactionEntityToBeSaveForStockEntry);
    }


    private Boolean checkStockEntryTimeComparingToNow(
            final LocalDateTime entryTime
    ) {
        return entryTime.isBefore(LocalDateTime.now());
    }

    private StockTransaction createStockTransactionForStockEntryForPastDate(
            final BigDecimal entryAmount,
            final LocalDateTime entryTime,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        StockTransactionEntity stockTransactionEntityBeforeEntryTime = stockTransactionRepository
                .findStockTransactionEntityByDateBefore(entryTime)
                .orElseThrow(() -> new RuntimeException("StockTranscation gecmisinde bozulma tespit edildi lutfen yoneticiye bildirin"));


        StockTransactionEntity stockTransactionEntityForStockEntry = StockTransactionMapper
                .mapForStockEntryForPastDate(
                        entryAmount,
                        entryTime,
                        stockTransactionEntityBeforeEntryTime.getAfterAmount(),
                        stock,
                        wareHouse
                );

        stockTransactionRepository.save(stockTransactionEntityForStockEntry);

        stockTransactionRepository.updateBeforeAmountAndAfterAmountAfterSpecifiedDate(
                entryTime,
                entryAmount
        );

        return StockTransactionMapper
                .toDomainModel(stockTransactionEntityForStockEntry);
    }
}
