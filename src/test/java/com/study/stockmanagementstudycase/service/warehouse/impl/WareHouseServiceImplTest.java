package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.impl.WareHouseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WareHouseServiceImplTest extends BaseServiceTest {
    @InjectMocks
    private WareHouseServiceImpl wareHouseService;

    @Mock
    private WareHouseRepository wareHouseRepository;

    @Test
    void givenWareHouseEntities_whenGetWareHouses_thenReturnWareHouseDomainModels() {
        // Given

        final int pageNumber = Faker.instance().number().randomDigitNotZero();
        final int pageSize = Faker.instance().number().randomDigitNotZero();


        final List<WareHouseEntity> mockWareHouseEntities = List.of(
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build()
        );

        // When
        Mockito.when(wareHouseRepository.findAll())
                .thenReturn(mockWareHouseEntities);

        // Then
        final List<WareHouse> response = wareHouseService.getWareHouses(pageNumber);

        Assertions.assertNotNull(response);

        Assertions.assertEquals(response.size(), 3);


        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findAll();
    }

    @Test
    void givenEmptyWareHouseEntities_whenGetWareHouses_thenThrowsException() {
        // Given
        final List<WareHouseEntity> mockWareHouseEntities = List.of();

        // When
        Mockito.when(wareHouseRepository.findAll())
                .thenReturn(mockWareHouseEntities);

        // Then
        Assertions.assertThrowsExactly(
                WareHouseNotFoundException.class,
                () -> wareHouseService.getWareHouses(Mockito.any())
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findAll();

    }

    @Test
    void givenWareHouseEntity_whenGetWareHouseById_thenReturnWareHouseDomainModel() {
        // Given
        final String mockWareHouseId = UUID.randomUUID().toString();
        final WareHouseEntity mockWareHouseEntity = WareHouseEntity.builder()
                .id(mockWareHouseId)
                .build();

        // When
        Mockito.when(wareHouseRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(mockWareHouseEntity));

        // Then
        final WareHouse response = wareHouseService.getWareHouseById(mockWareHouseId);

        Assertions.assertEquals(response.getId(), mockWareHouseId);

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(mockWareHouseId);

    }

    @Test
    void givenEmptyWareHouseEntity_whenGetWareHouseById_thenThrowsException() {
        // When
        Mockito.when(wareHouseRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                WareHouseNotFoundException.class,
                () -> wareHouseService.getWareHouseById(Mockito.anyString())
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(Mockito.anyString());

    }

}
