package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockUpdateServiceImpl implements StockUpdateService {

    private final StockRepository stockRepository;

    @Override
    public void updateStock(
            final StockUpdateRequest updateRequest,
            final String stockId) {


        final StockEntity stockEntityFromDb = stockRepository
                .findById(stockId)
                .orElseThrow(StockNotFoundException::new);

        StockMapper.mapForUpdating(updateRequest, stockEntityFromDb);

        stockRepository.save(stockEntityFromDb);

    }

}
