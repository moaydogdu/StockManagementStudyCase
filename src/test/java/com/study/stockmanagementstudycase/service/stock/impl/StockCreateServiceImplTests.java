package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.StockCreateRequestBuilder;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock.StockPurchaseService;
import com.study.stockmanagementstudycase.service.stockTransaction.StockTransactionCreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class StockCreateServiceImplTests extends BaseServiceTest {

    @InjectMocks
    private StockCreateServiceImpl stockCreateService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockTransactionCreateService stockTransactionCreateService;

    @Mock
    private StockPurchaseService stockPurchaseService;

    @Test
    void givenValidStockCreateRequestWithoutAmount_whenCreateStock_thenReturnStockDomainModel() {
        // Given
        final StockCreateRequest mockValidCreateRequest = new StockCreateRequestBuilder()
                .withValidFields()
                .withoutAmount()
                .build();


        final StockEntity mockStockEntity = StockMapper.mapForCreating(
                mockValidCreateRequest
        );

        mockStockEntity.setId(UUID.randomUUID().toString());

        final Stock mockStockDomainModel = StockMapper
                .toDomainModel(mockStockEntity);

        // When
        Mockito.when(stockRepository.save(Mockito.any(StockEntity.class)))
                .thenReturn(mockStockEntity);

        // Then
        Stock response = stockCreateService
                .createStock(mockValidCreateRequest);

        Assertions.assertEquals(
                mockStockDomainModel,
                response
        );

        // Verify
        Mockito.verify(
                        stockRepository,
                        Mockito.times(1)
                )
                .save(Mockito.any(StockEntity.class));

        Mockito.verify(
                        stockPurchaseService,
                        Mockito.times(0)
                )
                .purchaseStock(Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class));
    }

    @Test
    void givenValidStockCreateRequestWithAmount_whenCreateStock_thenCallPurchaseServiceAndReturnStockModel() {
        // Given
        final StockCreateRequest mockValidCreateRequest = new StockCreateRequestBuilder()
                .withValidFields()
                .build();

        final StockEntity mockStockEntity = StockMapper.mapForCreating(
                mockValidCreateRequest
        );

        mockStockEntity.setId(UUID.randomUUID().toString());

        final Stock mockStockDomainModel = StockMapper
                .toDomainModel(mockStockEntity);

        // When
        Mockito.when(stockRepository.save(Mockito.any(StockEntity.class)))
                .thenReturn(mockStockEntity);

        Mockito.when(stockPurchaseService.purchaseStock(
                        Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(mockStockDomainModel);

        // Then
        Stock response = stockCreateService
                .createStock(mockValidCreateRequest);

        // Verify
        Mockito.verify(
                        stockRepository,
                        Mockito.times(1)
                )
                .save(Mockito.any(StockEntity.class));

        Mockito.verify(
                        stockPurchaseService,
                        Mockito.times(1)
                )
                .purchaseStock(Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class));
    }

    @Test
    void givenValidStockCreateRequestWithAmountButServicesThrowsError_whenCreateStock_thenThrowError() {
        // Given
        final StockCreateRequest mockValidCreateRequest = new StockCreateRequestBuilder()
                .withValidFields()
                .build();

        final StockEntity mockStockEntity = StockMapper.mapForCreating(
                mockValidCreateRequest
        );

        mockStockEntity.setId(UUID.randomUUID().toString());

        final Stock mockStockDomainModel = StockMapper
                .toDomainModel(mockStockEntity);

        // When
        Mockito.when(stockRepository.save(Mockito.any(StockEntity.class)))
                .thenReturn(mockStockEntity);

        Mockito.when(stockTransactionCreateService.createStockTransactionForStockCreate(
                Mockito.any(StockCreateRequest.class),
                Mockito.any(Stock.class)
        )).thenThrow(new RuntimeException("Mock Error"));

        Mockito.when(stockPurchaseService.purchaseStock(
                        Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(mockStockDomainModel);

        // Then
        Assertions.assertThrows(
                RuntimeException.class,
                () -> stockCreateService.createStock(mockValidCreateRequest)
        );

        // Verify
        Mockito.verify(
                        stockRepository,
                        Mockito.times(1)
                )
                .save(Mockito.any(StockEntity.class));

        Mockito.verify(
                        stockPurchaseService,
                        Mockito.times(0)
                )
                .purchaseStock(Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class));
    }

}
