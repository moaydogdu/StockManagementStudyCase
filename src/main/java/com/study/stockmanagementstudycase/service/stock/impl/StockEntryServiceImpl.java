package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockEntryRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockEntryService;
import com.study.stockmanagementstudycase.service.stockTransaction.StockTransactionCreateService;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseService;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockEntryServiceImpl implements StockEntryService {

    private final StockRepository stockRepository;
    private final StockTransactionCreateService stockTransactionCreateService;
    private final WareHouseService wareHouseService;
    private final WareHouseStockUpdateService wareHouseStockUpdateService;


    @Override
    public Stock entryStock(
            final String stockId,
            final String wareHouseId,
            final BigDecimal entryAmount,
            final LocalDateTime entryTime
    ) {

        final StockEntity stockEntityFromDbForStockPurchase = stockRepository
                .findById(stockId)
                .orElseThrow(StockNotFoundException::new);

        final WareHouse wareHouseDomainModelForStockPurchase = wareHouseService
                .getWareHouseById(wareHouseId);

        this.updateStockAmountForEntryStock(
                stockEntityFromDbForStockPurchase,
                entryAmount
        );

        stockRepository.save(stockEntityFromDbForStockPurchase);

        final Stock stockDomainModel = StockMapper.toDomainModel(stockEntityFromDbForStockPurchase);

        wareHouseStockUpdateService.updateWareHouseStockForStockEntry(
                stockDomainModel,
                wareHouseDomainModelForStockPurchase,
                entryAmount,
                entryTime
        );

        stockTransactionCreateService.createStockTransactionForStockEntry(
                entryAmount,
                entryTime,
                stockDomainModel,
                wareHouseDomainModelForStockPurchase
        );

        return stockDomainModel;
    }

    @Override
    @Transactional
    public Stock entryStock(
            final String stockId,
            final StockEntryRequest stockEntryRequest
    ) {
        return this.entryStock(
                stockId,
                stockEntryRequest.getWareHouseId(),
                stockEntryRequest.getEntryAmount(),
                stockEntryRequest.getEntryTime()
        );
    }

    private void updateStockAmountForEntryStock(
            final StockEntity stockEntityForUpdateAmount,
            final BigDecimal entryAmount
    ) {
        stockEntityForUpdateAmount.setAmount(
                stockEntityForUpdateAmount.getAmount().add(entryAmount)
        );
    }
}
