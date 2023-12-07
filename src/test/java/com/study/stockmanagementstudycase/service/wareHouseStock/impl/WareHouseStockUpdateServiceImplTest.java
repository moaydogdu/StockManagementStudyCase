package com.study.stockmanagementstudycase.service.wareHouseStock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockCreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class WareHouseStockUpdateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private WareHouseStockUpdateServiceImpl wareHouseStockUpdateService;

    @Mock
    private WareHouseStockRepository wareHouseStockRepository;

    @Mock
    private WareHouseStockCreateService wareHouseStockCreateService;

    @Test
    void givenValidStockWareHouseEntryAmountAndEntryTime_whenUpdateWareHouseStockForStockEntry_thenReturnUpdatedWareHouseStockDomainModel() {
        // Given
        final Stock mockValidStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final WareHouse mockValidWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final BigDecimal oldAmount = BigDecimal.valueOf(5);
        final BigDecimal entryAmount = BigDecimal.TEN;
        final LocalDateTime entryTime = LocalDateTime.now().minusHours(1);

        final WareHouseStockEntity mockWareHouseStockEntity = WareHouseStockEntity.builder()
                .id(UUID.randomUUID().toString())
                .stockEntity(
                        StockMapper.toEntity(mockValidStockDomainModel)
                )
                .wareHouseEntity(
                        WareHouseMapper.toEntity(mockValidWareHouseDomainModel)
                )
                .amount(oldAmount)
                .build();

        // When
        Mockito.when(wareHouseStockRepository
                        .existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Boolean.TRUE);

        Mockito.when(wareHouseStockRepository
                        .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Optional.of(mockWareHouseStockEntity));

        // Then
        final WareHouseStock responseWareHouseStockDomainModel = wareHouseStockUpdateService
                .updateWareHouseStockForStockEntry(
                        mockValidStockDomainModel,
                        mockValidWareHouseDomainModel,
                        entryAmount,
                        entryTime
                );

        Assertions.assertNotNull(responseWareHouseStockDomainModel);
        Assertions.assertEquals(
                responseWareHouseStockDomainModel.getAmount(),
                oldAmount.add(entryAmount)
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).save(Mockito.any(WareHouseStockEntity.class));

    }

    @Test
    void givenNotExistWareHouseStock_whenUpdateWareHouseStockForStockEntry_thenReturnWareHouseStockDomainModel() {
        // Given
        final Stock mockValidStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final WareHouse mockValidWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final BigDecimal entryAmount = BigDecimal.TEN;
        final LocalDateTime entryTime = LocalDateTime.now().minusHours(1);

        // When
        Mockito.when(wareHouseStockRepository
                        .existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Boolean.FALSE);

        Mockito.when(wareHouseStockCreateService
                        .createWareHouseStockForStockEntry(
                                mockValidStockDomainModel,
                                mockValidWareHouseDomainModel,
                                entryAmount
                        ))
                .thenReturn(WareHouseStock.builder()
                        .id(UUID.randomUUID().toString())
                        .build()
                );

        // Then
        final WareHouseStock response = wareHouseStockUpdateService
                .updateWareHouseStockForStockEntry(
                        mockValidStockDomainModel,
                        mockValidWareHouseDomainModel,
                        entryAmount,
                        entryTime
                );

        Assertions.assertNotNull(response);

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(0)
        ).findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseStockEntity.class));


    }

    @Test
    void givenNullEntryAmountAndEntryTime_whenUpdateWareHouseStockForStockEntry_thenThrowsNullPointerException() {
        // Given
        final Stock mockValidStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final WareHouse mockValidWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final BigDecimal oldAmount = BigDecimal.valueOf(5);
        final BigDecimal entryAmount = null;
        final LocalDateTime entryTime = null;

        final WareHouseStockEntity mockWareHouseStockEntity = WareHouseStockEntity.builder()
                .id(UUID.randomUUID().toString())
                .stockEntity(
                        StockMapper.toEntity(mockValidStockDomainModel)
                )
                .wareHouseEntity(
                        WareHouseMapper.toEntity(mockValidWareHouseDomainModel)
                )
                .amount(oldAmount)
                .build();

        // When
        Mockito.when(wareHouseStockRepository
                        .existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Boolean.TRUE);

        Mockito.when(wareHouseStockRepository
                        .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Optional.of(mockWareHouseStockEntity));

        // Then
        Assertions.assertThrows(
                NullPointerException.class,
                () -> wareHouseStockUpdateService
                        .updateWareHouseStockForStockEntry(
                                mockValidStockDomainModel,
                                mockValidWareHouseDomainModel,
                                entryAmount,
                                entryTime
                        )
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseStockEntity.class));


    }

    @Test
    void givenZeroEntryAmount_whenUpdateWareHouseStockForStockEntry_thenThrowsException() {
        // Given
        final Stock mockValidStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final WareHouse mockValidWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final BigDecimal oldAmount = BigDecimal.valueOf(5);
        final BigDecimal entryAmount = BigDecimal.ZERO;
        final LocalDateTime entryTime = LocalDateTime.now().minusHours(1);

        final WareHouseStockEntity mockWareHouseStockEntity = WareHouseStockEntity.builder()
                .id(UUID.randomUUID().toString())
                .stockEntity(
                        StockMapper.toEntity(mockValidStockDomainModel)
                )
                .wareHouseEntity(
                        WareHouseMapper.toEntity(mockValidWareHouseDomainModel)
                )
                .amount(oldAmount)
                .build();

        // When
        Mockito.when(wareHouseStockRepository
                        .existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Boolean.TRUE);

        Mockito.when(wareHouseStockRepository
                        .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Optional.of(mockWareHouseStockEntity));

        // Then
        Assertions.assertThrows(
                Exception.class,
                () -> wareHouseStockUpdateService
                        .updateWareHouseStockForStockEntry(
                                mockValidStockDomainModel,
                                mockValidWareHouseDomainModel,
                                entryAmount,
                                entryTime
                        )
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseStockEntity.class));

    }

    @Test
    void givenFurtherEntryTime_whenUpdateWareHouseStockForStockEntry_thenThrowsException() {
        // Given
        final Stock mockValidStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final WareHouse mockValidWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final BigDecimal oldAmount = BigDecimal.valueOf(5);
        final BigDecimal entryAmount = BigDecimal.TEN;
        final LocalDateTime entryTime = LocalDateTime.now().plusMinutes(1);

        final WareHouseStockEntity mockWareHouseStockEntity = WareHouseStockEntity.builder()
                .id(UUID.randomUUID().toString())
                .stockEntity(
                        StockMapper.toEntity(mockValidStockDomainModel)
                )
                .wareHouseEntity(
                        WareHouseMapper.toEntity(mockValidWareHouseDomainModel)
                )
                .amount(oldAmount)
                .build();

        // When
        Mockito.when(wareHouseStockRepository
                        .existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Boolean.TRUE);

        Mockito.when(wareHouseStockRepository
                        .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                                Mockito.any(StockEntity.class),
                                Mockito.any(WareHouseEntity.class)
                        ))
                .thenReturn(Optional.of(mockWareHouseStockEntity));

        // Then
        Assertions.assertThrows(
                Exception.class,
                () -> wareHouseStockUpdateService
                        .updateWareHouseStockForStockEntry(
                                mockValidStockDomainModel,
                                mockValidWareHouseDomainModel,
                                entryAmount,
                                entryTime
                        )
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                Mockito.any(StockEntity.class),
                Mockito.any(WareHouseEntity.class)
        );

        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseStockEntity.class));

    }


}
