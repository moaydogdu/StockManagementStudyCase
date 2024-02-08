package com.study.stockmanagementstudycase.service.warehouse_stock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.common.exception.warehouse_stock.WareHouseStockNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class WareHouseStockServiceImplTest extends BaseServiceTest {
    @InjectMocks
    WareHouseStockServiceImpl wareHouseStockService;

    @Mock
    private WareHouseStockRepository wareHouseStockRepository;

    @Test
    void givenWareHouseStockEntities_whenGetWareHouseStocks_thenReturnWareHouseStockDomainModels() {
        // Given
        final StockEntity mockStockEntity = StockEntity.builder()
                .id(UUID.randomUUID().toString())
                .build();
        final WareHouseEntity mockWareHouseEntity = WareHouseEntity.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final List<WareHouseStockEntity> mockWareHouseStockEntities = List.of(
                WareHouseStockEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .stockEntity(mockStockEntity)
                        .wareHouseEntity(mockWareHouseEntity)
                        .build(),

                WareHouseStockEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .stockEntity(mockStockEntity)
                        .wareHouseEntity(mockWareHouseEntity)
                        .build(),

                WareHouseStockEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .stockEntity(mockStockEntity)
                        .wareHouseEntity(mockWareHouseEntity)
                        .build()
        );

        // When
        Mockito.when(wareHouseStockRepository.findAll())
                .thenReturn(mockWareHouseStockEntities);

        // Then
        final List<WareHouseStock> response = wareHouseStockService.getWareHouseStocks();

        Assertions.assertNotNull(response);

        Assertions.assertEquals(response.size(), 3);

        //Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findAll();
    }

    @Test
    void givenEmptyWareHouseStockEntities_whenGetWareHouseStocks_thenThrowsWareHouseStockNotFoundException() {
        // Given
        final List<WareHouseStockEntity> mockWareHouseStockEntities = List.of();

        // When
        Mockito.when(wareHouseStockRepository.findAll())
                .thenReturn(mockWareHouseStockEntities);

        // Then
        Assertions.assertThrowsExactly(
                WareHouseStockNotFoundException.class,
                () -> wareHouseStockService.getWareHouseStocks()
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findAll();

    }

    @Test
    void givenWareHouseStockEntity_whenGetWareHouseStockById_thenReturnWareHouseStockDomainModel() {
        // Given
        final String mockWareHouseStockId = UUID.randomUUID().toString();

        final StockEntity mockStockEntity = StockEntity.builder()
                .id(UUID.randomUUID().toString())
                .build();
        final WareHouseEntity mockWareHouseEntity = WareHouseEntity.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final WareHouseStockEntity mockWareHouseStockEntity = WareHouseStockEntity.builder()
                .id(mockWareHouseStockId)
                .stockEntity(mockStockEntity)
                .wareHouseEntity(mockWareHouseEntity)
                .build();

        // When
        Mockito.when(wareHouseStockRepository.findById(mockWareHouseStockId))
                .thenReturn(Optional.of(mockWareHouseStockEntity));

        // Then
        final WareHouseStock reponse = wareHouseStockService
                .getWareHouseStockById(mockWareHouseStockId);

        Assertions.assertEquals(reponse.getId(), mockWareHouseStockId);

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findById(mockWareHouseStockId);

    }

    @Test
    void givenEmptyWareHouseStockEntity_whenGetWareHouseStockById_thenThrowsWareHouseStockNotFoundException() {

        // When
        Mockito.when(wareHouseStockRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                WareHouseStockNotFoundException.class,
                () -> wareHouseStockService.getWareHouseStockById(Mockito.anyString())
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).findById(Mockito.anyString());

    }

}
