package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.stock.StockPagingRequestBuilder;
import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;
import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockPagingRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.repository.StockRepository;
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

class StockServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void givenStockEntities_whenGetStocks_thenReturnStockDomainModels() {
        // Given
        final CustomPagingRequest mockStockPagingRequest = new StockPagingRequestBuilder()
                .withValidFields()
                .build();

        final List<StockEntity> mockStockEntities = List.of(
                StockEntity.builder().id(UUID.randomUUID().toString()).build(),
                StockEntity.builder().id(UUID.randomUUID().toString()).build(),
                StockEntity.builder().id(UUID.randomUUID().toString()).build()
        );

        final Page<StockEntity> mockStockEntityPage = new PageImpl<>(
                mockStockEntities,
                mockStockPagingRequest.toPageable(),
                mockStockEntities.size()
        );

        // When
        Mockito.when(stockRepository.findAll(mockStockPagingRequest.toPageable()))
                .thenReturn(mockStockEntityPage);

        // Then
        final CustomPage<Stock> response = stockService
                .getStocks(mockStockPagingRequest);

        Assertions.assertEquals(
                mockStockEntities.size(),
                response.getTotalElementCount()
        );

        // Verify
        Mockito.verify(
                stockRepository, Mockito.times(1)
        ).findAll(mockStockPagingRequest.toPageable());

    }

    @Test
    public void givenEmptyStockEntities_whenGetStocks_thenThrowsException() {
        // Given
        final CustomPagingRequest mockStockPagingRequest = new StockPagingRequestBuilder()
                .withValidFields()
                .build();

        final List<StockEntity> mockStockEntities = List.of();

        final Page<StockEntity> mockStockEntityPage = new PageImpl<>(
                mockStockEntities,
                mockStockPagingRequest.toPageable(),
                mockStockEntities.size()
        );

        // When
        Mockito.when(stockRepository.findAll(mockStockPagingRequest.toPageable()))
                .thenReturn(mockStockEntityPage);

        // Then
        Assertions.assertThrows(
                StockNotFoundException.class,
                () -> stockService.getStocks(mockStockPagingRequest)
        );

        // Verify
        Mockito.verify(
                stockRepository, Mockito.times(1)
        ).findAll(mockStockPagingRequest.toPageable());

    }

    @Test
    public void givenStockEntity_whenGetStockById_thenReturnStockDomainModel() {
        // Given
        final String mockStockId = UUID.randomUUID().toString();

        final StockEntity mockStockEntity = StockEntity.builder()
                .id(mockStockId)
                .build();

        // When
        Mockito.when(stockRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(mockStockEntity));

        // Then
        final Stock response = stockService.getStockById(mockStockId);

        Assertions.assertEquals(
                response.getId(),
                mockStockId
        );

        // Verify
        Mockito.verify(
                stockRepository, Mockito.times(1)
        ).findById(mockStockId);

    }

    @Test
    public void givenEmptyStockEntity_whenGetStockById_thenThrowsException() {
        // When
        Mockito.when(stockRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                StockNotFoundException.class,
                () -> stockService.getStockById(Mockito.anyString())
        );

        // Verify
        Mockito.verify(
                stockRepository, Mockito.times(1)
        ).findById(Mockito.anyString());

    }

    @Test
    public void givenStockEntitiesWithStatusIsFalse_whenGetDeletedStocks_thenReturnCustomPage() {
        // Given
        final StockPagingRequest mockStockPagingRequest = new StockPagingRequestBuilder()
                .withValidFields()
                .build();

        final List<StockEntity> mockStockEntitiesWithStatusIsFalse = List.of(
                StockEntity.builder().id(UUID.randomUUID().toString()).build(),
                StockEntity.builder().id(UUID.randomUUID().toString()).build(),
                StockEntity.builder().id(UUID.randomUUID().toString()).build()
        );

        final Page<StockEntity> mockStockEntityPage = new PageImpl<>(
                mockStockEntitiesWithStatusIsFalse,
                mockStockPagingRequest.toPageable(),
                mockStockEntitiesWithStatusIsFalse.size()
        );

        // When
        Mockito.when(stockRepository.
                        findStockEntitiesByStatusIsFalse(mockStockPagingRequest.toPageable()))
                .thenReturn(mockStockEntityPage);

        // Then
        final CustomPage<Stock> response = stockService
                .getDeletedStocks(mockStockPagingRequest);

        Assertions.assertEquals(
                mockStockEntitiesWithStatusIsFalse.size(),
                response.getTotalElementCount()
        );

        // Verify
        Mockito.verify(stockRepository, Mockito.times(1))
                .findStockEntitiesByStatusIsFalse(mockStockPagingRequest.toPageable());

    }

    @Test
    public void givenEmptyStockEntities_whenGetDeletedStocks_thenThrowsStockNotFoundException() {
        // Given
        final StockPagingRequest mockStockPagingRequest = new StockPagingRequestBuilder()
                .withValidFields()
                .build();

        final List<StockEntity> mockStockEntitiesWithStatusIsFalse = List.of();

        final Page<StockEntity> mockStockEntityPage = new PageImpl<>(
                mockStockEntitiesWithStatusIsFalse,
                mockStockPagingRequest.toPageable(),
                mockStockEntitiesWithStatusIsFalse.size()
        );

        // When
        Mockito.when(stockRepository.
                        findStockEntitiesByStatusIsFalse(mockStockPagingRequest.toPageable()))
                .thenReturn(mockStockEntityPage);

        // Then
        Assertions.assertThrowsExactly(
                StockNotFoundException.class,
                () -> stockService.getDeletedStocks(mockStockPagingRequest)
        );

        // Verify
        Mockito.verify(stockRepository, Mockito.times(1))
                .findStockEntitiesByStatusIsFalse(mockStockPagingRequest.toPageable());
    }
}
