package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.common.exception.warehouse.UnableToDeleteWareHouseException;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

public class WareHouseDeleteServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private WareHouseDeleteServiceImpl wareHouseDeleteService;

    @Mock
    private WareHouseRepository wareHouseRepository;

    @Test
    void givenValidWareHouseEntity_whenDeleteWareHouse_thenDeleteWareHouseDomainModel() {
        // Given
        final String mockWareHouseId = UUID.randomUUID().toString();
        final WareHouseEntity mockWareHouseEntity = WareHouseEntity.builder()
                .id(mockWareHouseId)
                .status(true)
                .build();

        // When
        Mockito.when(wareHouseRepository.findById(
                mockWareHouseId)
        ).thenReturn(Optional.of(mockWareHouseEntity));

        // Then
        Assertions.assertDoesNotThrow(
                () -> wareHouseDeleteService.deleteWareHouse(mockWareHouseId)
        );

        Assertions.assertEquals(
                Boolean.FALSE, mockWareHouseEntity.getStatus()
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(mockWareHouseId);

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).save(mockWareHouseEntity);
    }

    @Test
    void givenNotValidWareHouseEntity_whenDeleteWareHouse_thenTrowsException() {
        // Given
        final String mockWareHouseId = UUID.randomUUID().toString();
        final WareHouseEntity mockWareHouseEntity = WareHouseEntity.builder()
                .id(mockWareHouseId)
                .status(false)
                .build();

        // When
        Mockito.when(wareHouseRepository.findById(
                mockWareHouseId)
        ).thenReturn(Optional.of(mockWareHouseEntity));

        // Then
        Assertions.assertThrowsExactly(
                UnableToDeleteWareHouseException.class,
                () -> wareHouseDeleteService.deleteWareHouse(mockWareHouseId)
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(mockWareHouseId);

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(0)
        ).save(mockWareHouseEntity);
    }

    @Test
    void givenNotFoundWareHouseEntity_whenDeleteWareHouse_thenTrowsException() {
        // When
        Mockito.when(wareHouseRepository.findById(
                Mockito.anyString())
        ).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                WareHouseNotFoundException.class,
                () -> wareHouseDeleteService.deleteWareHouse(Mockito.anyString())
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(Mockito.anyString());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseEntity.class));
    }

}
