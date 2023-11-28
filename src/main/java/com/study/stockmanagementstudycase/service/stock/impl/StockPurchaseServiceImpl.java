package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockPurchaseService;
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
public class StockPurchaseServiceImpl implements StockPurchaseService {

    private final StockRepository stockRepository;
    private final StockTransactionCreateService stockTransactionCreateService;
    private final WareHouseService wareHouseService;
    private final WareHouseStockUpdateService wareHouseStockUpdateService;

    @Override
    @Transactional
    public Stock purchaseStock(
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

        this.updateStockAmountForPurchaseStock(
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

    private void updateStockAmountForPurchaseStock(
            final StockEntity stockEntityForUpdateAmount,
            final BigDecimal entryAmount
    ) {
        stockEntityForUpdateAmount.setAmount(
                stockEntityForUpdateAmount.getAmount().add(entryAmount)
        );
    }
}
