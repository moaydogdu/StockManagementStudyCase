package com.study.stockmanagementstudycase.service.stock_transaction.impl;

import com.study.stockmanagementstudycase.common.exception.stock.UnableToSellStockException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.StockTransaction;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.stockTransaction.StockTransactionMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.StockTransactionRepository;
import com.study.stockmanagementstudycase.service.stock_transaction.StockTransactionCreateService;
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

        if (this.checkTransactionTimeComparingToNow(entryTime)) {
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


    private Boolean checkTransactionTimeComparingToNow(
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
        final StockTransactionEntity stockTransactionEntityBeforeEntryTime = stockTransactionRepository
                .findStockTransactionEntityByDateBefore(entryTime)
                .orElseThrow(() -> new RuntimeException("StockTranscation gecmisinde bozulma tespit edildi lutfen yoneticiye bildirin"));


        final StockTransactionEntity stockTransactionEntityForStockEntry = StockTransactionMapper
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

    @Override
    public StockTransaction createStockTransactionForStockSale(
            final BigDecimal saleAmount,
            final LocalDateTime saleTime,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        if (checkTransactionTimeComparingToNow(saleTime)) {
            return this.createStockTransactionForStockSaleForPastDate(
                    saleAmount,
                    saleTime,
                    stock,
                    wareHouse
            );
        }
        final StockTransactionEntity stockTransactionEntityForStockSale = StockTransactionMapper
                .mapForStockSale(
                        saleAmount,
                        saleTime,
                        stock,
                        wareHouse
                );

        stockTransactionRepository.save(stockTransactionEntityForStockSale);

        return StockTransactionMapper
                .toDomainModel(stockTransactionEntityForStockSale);
    }

    private StockTransaction createStockTransactionForStockSaleForPastDate(
            final BigDecimal saleAmount,
            final LocalDateTime saleTime,
            final Stock stock,
            final WareHouse wareHouse
    ) {
        final StockTransactionEntity stockTransactionEntityForSaleBeforeSaleTime = stockTransactionRepository
                .findStockTransactionEntityByDateBefore(saleTime)
                .orElseThrow(() -> new RuntimeException(
                        "StockTranscation gecmisinde bozulma tespit edildi lutfen yoneticiye bildirin"
                ));

        if (stockTransactionEntityForSaleBeforeSaleTime.getAfterAmount().compareTo(saleAmount)<0){
            throw new UnableToSellStockException("Belirtilen tarihte yeteri kadar stok bulunamadığı için satış yapılamaz.");
        }

        final StockTransactionEntity stockTransactionEntityForStockSale = StockTransactionMapper
                .mapForStockSaleForPastDate(
                        saleAmount,
                        saleTime,
                        stockTransactionEntityForSaleBeforeSaleTime.getAfterAmount(),
                        stock,
                        wareHouse
                );

        stockTransactionRepository.save(stockTransactionEntityForStockSale);

        stockTransactionRepository.updateBeforeAmountAndAfterAmountAfterSpecifiedDateBySubtractAmount(
                saleTime,
                saleAmount
        );

        return StockTransactionMapper
                .toDomainModel(stockTransactionEntityForStockSale);
    }
}
