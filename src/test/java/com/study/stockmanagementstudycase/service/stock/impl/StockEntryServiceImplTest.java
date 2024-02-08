package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.entity.StockEntityBuilder;
import com.study.stockmanagementstudycase.builder.model.WareHouseBuilder;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;
import com.study.stockmanagementstudycase.service.stock_transaction.StockTransactionCreateService;
import com.study.stockmanagementstudycase.service.warehouse.WareHouseService;
import com.study.stockmanagementstudycase.service.warehouse_stock.WareHouseStockUpdateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class StockEntryServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private StockEntryServiceImpl stockPurchaseService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockTransactionCreateService stockTransactionCreateService;

    @Mock
    private WareHouseStockUpdateService wareHouseStockUpdateService;

    @Mock
    private WareHouseService wareHouseService;

    @Test
    void givenStockRepositoryThrowsError_whenPurchaseStock_thenThrowsError() {
        // Given

        // When
        Mockito.when(stockRepository.findById(Mockito.any(String.class)))
                .thenThrow(new RuntimeException("Mock Error"));

        // Then
        Assertions.assertThrows(
                RuntimeException.class,
                () -> stockPurchaseService.entryStock(
                        Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class)
                )
        );

        // Verify
        Mockito.verify(
                stockRepository,
                Mockito.times(0)
        ).save(Mockito.any(StockEntity.class));


        Mockito.verify(
                wareHouseStockUpdateService,
                Mockito.times(0)
        ).updateWareHouseStockForStockEntry(
                Mockito.any(Stock.class),
                Mockito.any(WareHouse.class),
                Mockito.any(BigDecimal.class),
                Mockito.any(LocalDateTime.class)
        );

        Mockito.verify(
                stockTransactionCreateService,
                Mockito.times(0)
        ).createStockTransactionForStockEntry(
                Mockito.any(BigDecimal.class),
                Mockito.any(LocalDateTime.class),
                Mockito.any(Stock.class),
                Mockito.any(WareHouse.class)
        );

    }

    @Test
    void givenWareHouseServiceThrowsError_whenPurchaseStock_thenThrowsError() {
        // Given

        // When
        Mockito.when(wareHouseService.getWareHouseById(Mockito.any(String.class)))
                .thenThrow(new RuntimeException("Mock Error"));

        // Then
        Assertions.assertThrows(
                RuntimeException.class,
                () -> stockPurchaseService.entryStock(
                        Mockito.any(String.class),
                        Mockito.any(String.class),
                        Mockito.any(BigDecimal.class),
                        Mockito.any(LocalDateTime.class)
                )
        );

        // Verify
        Mockito.verify(
                stockRepository,
                Mockito.times(0)
        ).save(Mockito.any(StockEntity.class));

        Mockito.verify(
                wareHouseStockUpdateService,
                Mockito.times(0)
        ).updateWareHouseStockForStockEntry(
                Mockito.any(Stock.class),
                Mockito.any(WareHouse.class),
                Mockito.any(BigDecimal.class),
                Mockito.any(LocalDateTime.class)
        );

        Mockito.verify(
                stockTransactionCreateService,
                Mockito.times(0)
        ).createStockTransactionForStockEntry(
                Mockito.any(BigDecimal.class),
                Mockito.any(LocalDateTime.class),
                Mockito.any(Stock.class),
                Mockito.any(WareHouse.class)
        );

    }

    @Test
    void givenValidParameters_whenPurchaseStock_thenReturnStock() {
        // Given
        final String mockStockId = UUID.randomUUID().toString();
        final String mockWareHouseId = UUID.randomUUID().toString();
        final BigDecimal mockEntryAmount = BigDecimal.TEN;
        final LocalDateTime mockEntryTime = LocalDateTime.now();

        final StockEntity mockStockEntity = new StockEntityBuilder()
                .withValidFields()
                .withId(mockStockId)
                .build();

        final WareHouse mockWareHouse = new WareHouseBuilder()
                .withValidFields()
                .withId(mockWareHouseId)
                .build();

        Stock expectedStockDomainModel = StockMapper
                .toDomainModel(mockStockEntity);

        expectedStockDomainModel.setAmount(expectedStockDomainModel.getAmount().add(mockEntryAmount));

        // When
        Mockito.when(stockRepository.findById(Mockito.any(String.class)))
                .thenReturn(Optional.of(mockStockEntity));

        Mockito.when(wareHouseService.getWareHouseById(Mockito.any(String.class)))
                .thenReturn(mockWareHouse);

        // Then
        Stock response = stockPurchaseService.entryStock(
                mockStockId,
                mockWareHouseId,
                mockEntryAmount,
                mockEntryTime
        );

        Assertions.assertEquals(
                expectedStockDomainModel,
                response
        );

        // Verfiy
        Mockito.verify(
                stockRepository,
                Mockito.times(1)
        ).save(Mockito.any(StockEntity.class));

        Mockito.verify(
                wareHouseStockUpdateService,
                Mockito.times(1)
        ).updateWareHouseStockForStockEntry(
                Mockito.any(Stock.class),
                Mockito.any(WareHouse.class),
                Mockito.any(BigDecimal.class),
                Mockito.any(LocalDateTime.class)
        );

        Mockito.verify(
                stockTransactionCreateService,
                Mockito.times(1)
        ).createStockTransactionForStockEntry(
                Mockito.any(BigDecimal.class),
                Mockito.any(LocalDateTime.class),
                Mockito.any(Stock.class),
                Mockito.any(WareHouse.class)
        );
    }

}
