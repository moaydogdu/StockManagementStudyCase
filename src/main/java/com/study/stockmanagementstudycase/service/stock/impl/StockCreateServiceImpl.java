package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import com.study.stockmanagementstudycase.service.stock.StockPurchaseService;
import com.study.stockmanagementstudycase.service.stockTransaction.StockTransactionCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StockCreateServiceImpl implements StockCreateService {

    private final StockRepository stockRepository;
    private final StockPurchaseService stockPurchaseService;
    private final StockTransactionCreateService stockTransactionCreateService;

    @Override
    @Transactional
    public Stock createStock(
            final StockCreateRequest stockCreateRequest
    ) {
        StockEntity stockEntityToBeCreate = StockMapper.mapForCreating(
                stockCreateRequest
        );

        stockEntityToBeCreate = stockRepository.save(stockEntityToBeCreate);

        Stock stockDomainModel = StockMapper.toDomainModel(stockEntityToBeCreate);

        stockTransactionCreateService.createStockTransactionForStockCreate(
                stockCreateRequest,
                stockDomainModel
        );

        if (Objects.nonNull(stockCreateRequest.getAmount())
                && Boolean.TRUE.equals(stockCreateRequest.getAmount().compareTo(BigDecimal.ZERO) > 0)
        ) {
            return stockPurchaseService.purchaseStock(
                    stockEntityToBeCreate.getId(),
                    stockCreateRequest.getWareHouseId(),
                    stockCreateRequest.getAmount(),
                    stockCreateRequest.getDateTime()
            );
        }

        return stockDomainModel;
    }
}
