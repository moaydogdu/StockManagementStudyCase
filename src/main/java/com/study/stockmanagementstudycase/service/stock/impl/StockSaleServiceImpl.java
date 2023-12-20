package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.common.exception.stock.UnableToSellStockException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockSaleRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockSaleService;
import com.study.stockmanagementstudycase.service.stockTransaction.StockTransactionCreateService;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseService;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockService;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StockSaleServiceImpl implements StockSaleService {

    private final StockRepository stockRepository;
    private final WareHouseStockService wareHouseStockService;
    private final WareHouseService wareHouseService;
    private final StockTransactionCreateService stockTransactionCreateService;
    private final WareHouseStockUpdateService wareHouseStockUpdateService;

    @Override
    @Transactional
    public void saleStockById(
            final String stockId,
            final StockSaleRequest stockSaleRequest
    ) {
        final StockEntity stockEntityForSale = stockRepository
                .findById(stockId)
                .orElseThrow(StockNotFoundException::new);

        final WareHouse wareHouseDomainModelForSale = wareHouseService
                .getWareHouseById(stockSaleRequest.getWareHouseId());

        this.checkStockAmountForSale(
                stockSaleRequest.getAmount(),
                stockEntityForSale,
                stockSaleRequest.getWareHouseId()
        );

        this.subtractStockAmountForSale(
                stockSaleRequest.getAmount(),
                stockEntityForSale
        );

        stockRepository.save(stockEntityForSale);

        final Stock stockDomainModelForSale = StockMapper.toDomainModel(stockEntityForSale);

        stockTransactionCreateService.createStockTransactionForStockSale(
                stockSaleRequest.getAmount(),
                stockSaleRequest.getSaleTime(),
                stockDomainModelForSale,
                wareHouseDomainModelForSale
        );

        wareHouseStockUpdateService.updateWareHouseStockForStockSale(
                stockDomainModelForSale,
                wareHouseDomainModelForSale,
                stockSaleRequest.getAmount(),
                stockSaleRequest.getSaleTime()
        );
    }

    private void checkStockAmountForSale(
            final BigDecimal saleAmount,
            final StockEntity stockEntityForSale,
            final String wareHouseId
    ) {
        if (stockEntityForSale.getAmount().compareTo(saleAmount)<0) {
            throw new UnableToSellStockException("Mevcut stok miktarı yetersiz.");
        }

        final WareHouseStock wareHouseStock = wareHouseStockService
                .getWareHouseStockByStockIdAndWareHouseId(
                        stockEntityForSale.getId(),
                        wareHouseId
                );

        if (wareHouseStock.getAmount().compareTo(saleAmount)<0){
            throw new UnableToSellStockException("Mevcut stok miktarı yetersiz.");
        }
    }

    private void subtractStockAmountForSale(
            final BigDecimal saleAmount,
            final StockEntity stockEntityForSale
    ) {
        stockEntityForSale.setAmount(
                stockEntityForSale.getAmount().subtract(saleAmount)
        );
    }
}
