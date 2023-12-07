package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    @Override
    public List<Stock> getStocks() {
        List<StockEntity> stocksFromDB = stockRepository.findAll();

        return StockMapper.toDomainModel(stocksFromDB);
    }

    @Override
    public Stock getStockById(final String stockId) {
       final StockEntity stockfromDB = stockRepository.findById(stockId).orElseThrow(StockNotFoundException::new);
        return StockMapper.toDomainModel(stockfromDB);
    }
}
