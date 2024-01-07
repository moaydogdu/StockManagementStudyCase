package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.wareHouse.WareHousePagingRequestBuilder;
import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHousePagingRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.impl.WareHouseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WareHouseServiceImplTest extends BaseServiceTest {
    @InjectMocks
    private WareHouseServiceImpl wareHouseService;

    @Mock
    private WareHouseRepository wareHouseRepository;

    @Test
    void givenWareHouseEntitiesWithStatusTrue_whenGetWareHouses_thenReturnWareHouseDomainModels() {
        // Given
        final WareHousePagingRequest mockWareHousePagingRequest = new WareHousePagingRequestBuilder()
                .withValidFields()
                .build();

        final List<WareHouseEntity> mockWareHouseEntities = List.of(
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build()
        );

        final Page<WareHouseEntity> mockWareHouseEntityPage = new PageImpl<>(
                mockWareHouseEntities,
                mockWareHousePagingRequest.toPageable(),
                mockWareHouseEntities.size()
        );

        // When
        Mockito.when(wareHouseRepository
                        .findWareHouseEntitiesByStatusIsTrue(mockWareHousePagingRequest.toPageable())
                )
                .thenReturn(mockWareHouseEntityPage);

        // Then
        final CustomPage<WareHouse> response = wareHouseService
                .getWareHouses(mockWareHousePagingRequest);

        Assertions.assertEquals(
                mockWareHouseEntities.size(),
                response.getTotalElementCount()
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findWareHouseEntitiesByStatusIsTrue(mockWareHousePagingRequest.toPageable());
    }

    @Test
    void givenEmptyWareHouseEntities_whenGetWareHouses_thenThrowsException() {
        // Given
        final WareHousePagingRequest mockWareHousePagingRequest = new WareHousePagingRequestBuilder()
                .withValidFields()
                .build();

        final List<WareHouseEntity> mockWareHouseEntities = List.of();

        final Page<WareHouseEntity> mockWareHouseEntityPage = new PageImpl<>(
                mockWareHouseEntities,
                mockWareHousePagingRequest.toPageable(),
                mockWareHouseEntities.size()
        );

        // When
        Mockito.when(wareHouseRepository
                        .findWareHouseEntitiesByStatusIsTrue(mockWareHousePagingRequest.toPageable())
                )
                .thenReturn(mockWareHouseEntityPage);

        // Then
        Assertions.assertThrowsExactly(
                WareHouseNotFoundException.class,
                () -> wareHouseService.getWareHouses(mockWareHousePagingRequest)
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findWareHouseEntitiesByStatusIsTrue(mockWareHousePagingRequest.toPageable());

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

    @Test
    void givenWareHouseEntitiesWithStatusIsFalse_whenGetDeletedWareHouses_thenReturnCustomPage() {
        // Given
        final WareHousePagingRequest mockWareHousePagingRequest = new WareHousePagingRequestBuilder()
                .withValidFields()
                .build();

        final List<WareHouseEntity> mockWareHouseEntitiesWithStatusIsFalse = List.of(
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).status(false).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).status(false).build()
        );

        final Page<WareHouseEntity> mockWareHouseEntityPage = new PageImpl<>(
                mockWareHouseEntitiesWithStatusIsFalse,
                mockWareHousePagingRequest.toPageable(),
                mockWareHouseEntitiesWithStatusIsFalse.size()
        );

        // When
        Mockito.when(wareHouseRepository
                        .findWareHouseEntitiesByStatusIsFalse(mockWareHousePagingRequest.toPageable()))
                .thenReturn(mockWareHouseEntityPage);

        // Then
        final CustomPage<WareHouse> response = wareHouseService
                .getDeletedWareHouses(mockWareHousePagingRequest);

        Assertions.assertEquals(
                mockWareHouseEntitiesWithStatusIsFalse.size(),
                response.getTotalElementCount()
        );

        // Verify
        Mockito.verify(wareHouseRepository, Mockito.times(1))
                .findWareHouseEntitiesByStatusIsFalse(mockWareHousePagingRequest.toPageable());

    }

    @Test
    void givenEmptyWareHouseEntities_whenGetDeletedWareHouses_thenThrowsWareHouseNotFoundException() {
        // Given
        final WareHousePagingRequest mockWareHousePagingRequest = new WareHousePagingRequestBuilder()
                .withValidFields()
                .build();

        final List<WareHouseEntity> mockWareHouseEntitiesWithStatusIsFalse = List.of();

        final Page<WareHouseEntity> mockWareHouseEntityPage = new PageImpl<>(
                mockWareHouseEntitiesWithStatusIsFalse,
                mockWareHousePagingRequest.toPageable(),
                mockWareHouseEntitiesWithStatusIsFalse.size()
        );

        // When
        Mockito.when(wareHouseRepository
                        .findWareHouseEntitiesByStatusIsFalse(mockWareHousePagingRequest.toPageable()))
                .thenReturn(mockWareHouseEntityPage);

        // Then
        Assertions.assertThrowsExactly(
                WareHouseNotFoundException.class,
                () -> wareHouseService.getDeletedWareHouses(mockWareHousePagingRequest)
        );

        // Verify
        Mockito.verify(wareHouseRepository, Mockito.times(1))
                .findWareHouseEntitiesByStatusIsFalse(mockWareHousePagingRequest.toPageable());

    }

    @Test
    void givenValidWareHousePagingRequestAndWareHouseEntities_whenGetAllWareHouses_thenReturnWareHousesWithAllStatuses() {
        // Given
        final WareHousePagingRequest mockWareHousePagingRequest = new WareHousePagingRequestBuilder()
                .withValidFields()
                .build();

        final List<WareHouseEntity> mockWareHouseEntities = List.of(
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).status(false).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).status(false).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build(),
                WareHouseEntity.builder().id(UUID.randomUUID().toString()).build()
        );

        final Page<WareHouseEntity> mockWareHouseEntityPage = new PageImpl<>(
                mockWareHouseEntities,
                mockWareHousePagingRequest.toPageable(),
                mockWareHouseEntities.size()
        );

        // When
        Mockito.when(wareHouseRepository.findAll(mockWareHousePagingRequest.toPageable()))
                .thenReturn(mockWareHouseEntityPage);

        // Then
        final CustomPage<WareHouse> response = wareHouseService
                .getAllWareHouses(mockWareHousePagingRequest);

        Assertions.assertEquals(
                mockWareHouseEntities.size(),
                response.getContent().size()
        );

        Assertions.assertEquals(
                mockWareHousePagingRequest.getPagination().getPageNumber() + 1,
                response.getPageNumber()
        );

        Assertions.assertEquals(
                mockWareHousePagingRequest.getPagination().getPageSize(),
                response.getPageSize()
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findAll(mockWareHousePagingRequest.toPageable());
    }

    @Test
    void givenValidWareHousePagingRequestButEmptyWareHouseEntities_whenGetAllWareHouses_thenThrowsWareHouseNotFoundException() {
        // Given
        final WareHousePagingRequest mockWareHousePagingRequest = new WareHousePagingRequestBuilder()
                .withValidFields()
                .build();

        final List<WareHouseEntity> mockWareHouseEntities = List.of();

        final Page<WareHouseEntity> mockWareHouseEntityPage = new PageImpl<>(
                mockWareHouseEntities,
                mockWareHousePagingRequest.toPageable(),
                mockWareHouseEntities.size()
        );

        // When
        Mockito.when(wareHouseRepository.findAll(mockWareHousePagingRequest.toPageable()))
                .thenReturn(mockWareHouseEntityPage);

        // Then
        Assertions.assertThrowsExactly(
                WareHouseNotFoundException.class,
                () -> wareHouseService.getAllWareHouses(mockWareHousePagingRequest)
        );

        // Verify
        Mockito.verify(
                wareHouseRepository,
                Mockito.times(1)
        ).findAll(mockWareHousePagingRequest.toPageable());
    }


}
