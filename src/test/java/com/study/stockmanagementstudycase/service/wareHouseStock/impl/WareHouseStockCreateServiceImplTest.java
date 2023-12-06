package com.study.stockmanagementstudycase.service.wareHouseStock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.dto.Stock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

public class WareHouseStockCreateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private WareHouseStockCreateServiceImpl wareHouseStockCreateService;

    @Mock
    private WareHouseStockRepository wareHouseStockRepository;

    @Test
    void givenValidStockAndWareHouse_whenCreateWareHouseStockForStockEntry_thenReturnWareHouseStockDomainModel() {
        // Given
        final Stock mockStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .amount(BigDecimal.TEN)
                .build();

        final WareHouse mockWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        // Then
        final WareHouseStock response = wareHouseStockCreateService
                .createWareHouseStockForStockEntry(
                        mockStockDomainModel,
                        mockWareHouseDomainModel
                );

        Assertions.assertNotNull(response);

        Assertions.assertEquals(
                response.getAmount(),
                mockStockDomainModel.getAmount()
        );

        Assertions.assertEquals(
                response.getStockEntityId(),
                mockStockDomainModel.getId()
        );

        Assertions.assertEquals(
                response.getWareHouseEntityId(),
                mockWareHouseDomainModel.getId()
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(1)
        ).save(Mockito.any(WareHouseStockEntity.class));

    }

    @Test
    void givenNotValidStockAndWareHouse_whenCreateWareHouseStockForStockEntry_thenThrowsNullPointerException() {
        // Then
        Assertions.assertThrows(
                NullPointerException.class,
                () -> wareHouseStockCreateService.createWareHouseStockForStockEntry(
                        null,
                        null
                )
        );

        // Verify
        Mockito.verify(
                wareHouseStockRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseStockEntity.class));
    }
}
