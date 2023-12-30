package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public CustomPage<Stock> getStocks(
            final CustomPagingRequest customPagingRequest
    ) {
        final Page<StockEntity> stockEntityListPage = stockRepository
                .findAll(customPagingRequest.toPageable());

        if (stockEntityListPage.isEmpty()) {
            throw new StockNotFoundException();
        }

        List<Stock> stockDomainModels = StockMapper.
                toDomainModel(stockEntityListPage.getContent());

        return CustomPage.of(
                stockDomainModels,
                stockEntityListPage
        );
    }

    @Override
    public Stock getStockById(
            final String stockId
    ) {
        final StockEntity stockFromDB = stockRepository
                .findById(stockId)
                .orElseThrow(StockNotFoundException::new);

        return StockMapper.toDomainModel(stockFromDB);
    }
}
