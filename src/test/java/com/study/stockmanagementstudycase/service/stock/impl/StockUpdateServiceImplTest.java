package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.StockUpdateRequestBuilder;
import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;


public class StockUpdateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private StockUpdateServiceImpl stockUpdateService;

    @Mock
    private StockRepository stockRepository;

    @Test
    void givenValidStockUpdateRequest_whenUpdateStock_thenUpdateStock() {

        // Given
        Stock stock = new Stock();
        String stockId = stock.getId();

        final StockUpdateRequest validStockUpdateRequest = new StockUpdateRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(
                stockRepository.findById(stockId)
        ).thenReturn(Optional.of(new StockEntity()));

        // Then
        Assertions.assertDoesNotThrow(
                () -> stockUpdateService.updateStock(
                        validStockUpdateRequest,
                        stockId
                )
        );

        // Verify
        Mockito.verify(
                stockRepository,
                Mockito.times(1)
        ).findById(stockId);

        Mockito.verify(
                stockRepository,
                Mockito.times(1)
        ).save(Mockito.any(StockEntity.class));

    }

    @Test
    void givenEmptyStockEntity_whenUpdateStock_thenThrowsStockNotFoundException() {

        // Given
        StockUpdateRequest validStockUpdateRequest = new StockUpdateRequestBuilder()
                .withValidFields()
                .build();

        final String mockStockId = UUID.randomUUID().toString();

        // When
        Mockito.when(
                stockRepository.findById(mockStockId)
        ).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                StockNotFoundException.class,
                () -> stockUpdateService.updateStock(
                        validStockUpdateRequest,
                        mockStockId
                )
        );

        // Verify
        Mockito.verify(
                stockRepository,
                Mockito.times(1)
        ).findById(mockStockId);

        Mockito.verify(
                stockRepository,
                Mockito.times(0)
        ).save(Mockito.any(StockEntity.class));

    }

}
