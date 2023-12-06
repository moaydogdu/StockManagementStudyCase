package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.WareHouseCreateRequestBuilder;
import com.study.stockmanagementstudycase.common.exception.WareHouseAlreadyExistException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.impl.WareHouseCreateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class WareHouseCreateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private WareHouseCreateServiceImpl wareHouseCreateService;

    @Mock
    private WareHouseRepository wareHouseRepository;

    @Test
    void givenValidWareHouseCreateRequest_whenCreateWareHouse_thenReturnWareHouseDomainModel() {
        // Given
        final WareHouseCreateRequest validWareHouseCreateRequest = new WareHouseCreateRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(
                        Mockito.anyString(),
                        Mockito.anyString())
                )
                .thenReturn(Boolean.FALSE);


        // Then
        final WareHouse response = wareHouseCreateService
                .createWareHouse(validWareHouseCreateRequest);

        Assertions.assertEquals(response.getName(), validWareHouseCreateRequest.getName());
        Assertions.assertEquals(response.getAddress(), validWareHouseCreateRequest.getAddress());

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).save(Mockito.any(WareHouseEntity.class));

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).existsWareHouseEntitiesByNameAndAddress(Mockito.anyString(), Mockito.anyString());

    }

    @Test
    void givenNotValidWareHouseCreateRequest_whenCreateWareHouse_thenThrowsException() {
        // Given
        final WareHouseCreateRequest notValidWareHouseCreateRequest = new WareHouseCreateRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(Boolean.TRUE);

        // Then
        Assertions.assertThrows(
                WareHouseAlreadyExistException.class,
                () -> wareHouseCreateService.createWareHouse(notValidWareHouseCreateRequest)
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).existsWareHouseEntitiesByNameAndAddress(Mockito.anyString(), Mockito.anyString());

        Mockito.verify(
                wareHouseRepository,
                Mockito.times(0)
        ).save(Mockito.any(WareHouseEntity.class));

    }

}
