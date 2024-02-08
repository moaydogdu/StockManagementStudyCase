package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.stock.StockNotFoundException;
import com.study.stockmanagementstudycase.common.exception.stock.UnableToDeleteStockException;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockDeleteServiceImpl implements StockDeleteService {

    private final StockRepository stockRepository;

    @Override
    public void deleteStock(
            final String stockId
    ) {
        final StockEntity stockEntityToBeDelete = stockRepository
                .findById(stockId)
                .orElseThrow(StockNotFoundException::new);

        if (Boolean.FALSE.equals(stockEntityToBeDelete.getStatus())) {
            throw new UnableToDeleteStockException("Stock zaten silinmiş");
        }

        stockEntityToBeDelete.setStatus(false);

        stockRepository.save(stockEntityToBeDelete);
    }

}

