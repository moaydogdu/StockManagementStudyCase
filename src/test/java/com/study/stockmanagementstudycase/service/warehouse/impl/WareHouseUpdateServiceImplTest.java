package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.wareHouse.WareHouseUpdateRequestBuilder;
import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseAlreadyExistException;
import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

public class WareHouseUpdateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private WareHouseUpdateServiceImpl wareHouseUpdateService;

    @Mock
    private WareHouseRepository wareHouseRepository;

    @Test
    void givenValidWareHouseUpdateRequest_whenUpdateWareHouse_thenUpdateWareHouse() {
        // Given
        WareHouse wareHouse = new WareHouse();
        String wareHouseId = wareHouse.getId();

        final WareHouseUpdateRequest validWareHouseUpdateRequest = new WareHouseUpdateRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(
                wareHouseRepository.findById(
                        wareHouseId)
        ).thenReturn(Optional.of(new WareHouseEntity()));

        Mockito.when(
                wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(
                        Mockito.anyString(),
                        Mockito.anyString()
                )
        ).thenReturn(Boolean.FALSE);

        // Then
        Assertions.assertDoesNotThrow(
                () -> wareHouseUpdateService.updateWareHouse(
                        validWareHouseUpdateRequest,
                        wareHouseId
                )
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(wareHouseId);

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).existsWareHouseEntitiesByNameAndAddress(Mockito.anyString(), Mockito.anyString());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).save(Mockito.any(WareHouseEntity.class));
    }

    @Test
    void givenExistWareHouseUpdateRequest_whenUpdateWareHouse_thenThrowsWareHouseAlreadyExistException() {
        // Given
        final WareHouseUpdateRequest notValidWareHouseUpdateRequest = new WareHouseUpdateRequestBuilder()
                .withValidFields()
                .build();
        final String mockWareHouseId = UUID.randomUUID().toString();

        // When
        Mockito.when(wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(
                notValidWareHouseUpdateRequest.getName(),
                notValidWareHouseUpdateRequest.getAddress()
        )).thenReturn(Boolean.TRUE);

        // Then
        Assertions.assertThrows(
                WareHouseAlreadyExistException.class,
                () -> wareHouseUpdateService.updateWareHouse(
                        notValidWareHouseUpdateRequest,
                        mockWareHouseId
                )
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).existsWareHouseEntitiesByNameAndAddress(Mockito.anyString(), Mockito.anyString());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(0)
        ).findById(Mockito.anyString());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseEntity.class));
    }

    @Test
    void givenEmptyWareHouseEntity_whenUpdateWareHouse_thenThrowsWareHouseNotFoundException() {
        // Given
        final WareHouseUpdateRequest validWareHouseUpdateRequest = new WareHouseUpdateRequestBuilder()
                .withValidFields()
                .build();
        final String mockWareHouseId = UUID.randomUUID().toString();

        // When
        Mockito.when(wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(
                validWareHouseUpdateRequest.getName(),
                validWareHouseUpdateRequest.getAddress()
        )).thenReturn(Boolean.FALSE);

        Mockito.when(wareHouseRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                WareHouseNotFoundException.class,
                () -> wareHouseUpdateService.updateWareHouse(
                        validWareHouseUpdateRequest,
                        mockWareHouseId
                )
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findById(Mockito.anyString());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).existsWareHouseEntitiesByNameAndAddress(
                validWareHouseUpdateRequest.getName(),
                validWareHouseUpdateRequest.getAddress());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseEntity.class));
    }

}
