package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.impl.WareHouseDeleteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class WareHouseDeleteServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private WareHouseDeleteServiceImpl wareHouseDeleteService;

    @Mock
    private WareHouseRepository wareHouseRepository;

    @Test
    void givenValidWareHouseId_whenDeleteWareHouse_thenDeleteWareHouseDomainModel() {
        // Given
        WareHouse wareHouse = new WareHouse();
        String wareHouseId = wareHouse.getId();

        // When
        Mockito.when(wareHouseRepository.findById(
                wareHouseId)
        ).thenReturn(Optional.of(new WareHouseEntity()));

        // Then
        Assertions.assertDoesNotThrow(
                () -> wareHouseDeleteService.deleteWareHouse(wareHouseId)
        );

        // Verify
        Mockito.verify(
                        wareHouseRepository
                        , Mockito.times(1))
                .deleteById(wareHouseId);
    }

    @Test
    void givenNotValidWareHouseId_whenDeleteWareHouse_thenTrowsException() {
        // Given
        WareHouse wareHouse = new WareHouse();
        String wareHouseId = wareHouse.getId();

        // When
        Mockito.when(wareHouseRepository.findById(
                wareHouseId)
        ).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                WareHouseNotFoundException.class,
                () -> wareHouseDeleteService.deleteWareHouse(wareHouseId)
        );

        // Verify

        Mockito.verify(
                        wareHouseRepository
                        , Mockito.times(1))
                .findById(wareHouseId);

        Mockito.verify(
                        wareHouseRepository
                        , Mockito.times(0))
                .deleteById(wareHouseId);
    }

}
