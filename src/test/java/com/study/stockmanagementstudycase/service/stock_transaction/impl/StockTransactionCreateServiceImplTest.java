package com.study.stockmanagementstudycase.service.stock_transaction.impl;

import com.study.stockmanagementstudycase.base.BaseServiceTest;
import com.study.stockmanagementstudycase.builder.dto.stock.StockCreateRequestBuilder;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.StockTransaction;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import com.study.stockmanagementstudycase.model.enums.StockTransactionType;
import com.study.stockmanagementstudycase.repository.StockTransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class StockTransactionCreateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private StockTransactionCreateServiceImpl stockTransactionCreateService;

    @Mock
    private StockTransactionRepository stockTransactionRepository;

    /**
     * Unit Test of create stockTransaction for stock create. Positive scenario.
     */
    @Test
    void givenValidStockCreateRequestAndStockDomainModel_whenCreateStockTransactionForStockCreate_thenReturnStockTransactionModel() {
        // Given
        final StockCreateRequest validStockCreateRequest = new StockCreateRequestBuilder()
                .withValidFields()
                .build();

        final Stock mockStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        // Then
        final StockTransaction response = stockTransactionCreateService
                .createStockTransactionForStockCreate(
                        validStockCreateRequest,
                        mockStockDomainModel
                );

        org.assertj.core.api.Assertions.assertThat(response.getAmount())
                .isEqualByComparingTo(BigDecimal.ZERO);

        org.assertj.core.api.Assertions.assertThat(response.getBeforeAmount())
                .isEqualByComparingTo(BigDecimal.ZERO);

        org.assertj.core.api.Assertions.assertThat(response.getAfterAmount())
                .isEqualByComparingTo(BigDecimal.ZERO);

        Assertions.assertEquals(
                response.getDate(),
                validStockCreateRequest.getDateTime()
        );

        Assertions.assertEquals(
                response.getStockTransactionType(),
                StockTransactionType.STOCK_CREATE
        );

        // Verify
        Mockito.verify(
                stockTransactionRepository,
                Mockito.times(1)
        ).save(Mockito.any(StockTransactionEntity.class));
    }

    /**
     * Unit Test of create stockTransaction for stock create. Negative scenario.
     */
    @Test
    void givenNullStockDomainModel_whenCreateStockTransactionForStockCreate_thenThrowsError() {
        // Given
        final StockCreateRequest validStockCreateRequest = new StockCreateRequestBuilder()
                .withValidFields()
                .build();

        // Then
        Assertions.assertThrows(
                NullPointerException.class,
                () -> stockTransactionCreateService.createStockTransactionForStockCreate(
                        validStockCreateRequest,
                        null
                ));

        // Verify
        Mockito.verify(
                        stockTransactionRepository,
                        Mockito.times(0))
                .save(Mockito.any(StockTransactionEntity.class));

    }

    /**
     * Unit test of create stockTransaction for stock entry. Positive scenario.
     */
    @Test
    void givenValidParameters_whenCreateStockTransactionForStockEntry_thenReturnStockTransaction() {
        // Given
        final WareHouse mockWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final Stock mockStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .amount(BigDecimal.TEN)
                .build();

        final BigDecimal mockEntryAmount = BigDecimal.TEN;
        final LocalDateTime mockEntryTime = LocalDateTime.now().plusHours(1);

        // Then
        final StockTransaction response = stockTransactionCreateService
                .createStockTransactionForStockEntry(
                        mockEntryAmount,
                        mockEntryTime,
                        mockStockDomainModel,
                        mockWareHouseDomainModel
                );

        Assertions.assertEquals(response.getStockTransactionType(),
                StockTransactionType.STOCK_ENTRY);

        Assertions.assertEquals(response.getAmount(), mockEntryAmount);

        Assertions.assertEquals(response.getBeforeAmount(),
                mockStockDomainModel.getAmount().subtract(mockEntryAmount));

        Assertions.assertEquals(response.getAfterAmount(),
                mockStockDomainModel.getAmount());

        Assertions.assertEquals(response.getDate(),
                mockEntryTime);

        // Verify
        Mockito.verify(
                stockTransactionRepository,
                Mockito.times(1)
        ).save(Mockito.any(StockTransactionEntity.class));

    }

    /**
     * Unit Test of create stockTransaction for stock entry. Negative scenario.
     */
    @Test
    void givenNullParameters_whenCreateStockTransactionForStockEntry_thenThrowsException() {
        // Then
        Assertions.assertThrows(
                NullPointerException.class,
                () -> stockTransactionCreateService.createStockTransactionForStockEntry(
                        null,
                        null,
                        null,
                        null
                )
        );

        // Verify
        Mockito.verify(
                stockTransactionRepository,
                Mockito.times(0)
        ).save(Mockito.any(StockTransactionEntity.class));

    }


    @Test
    void givenValidParameters_whenCreateStockTransactionForStockEntryForPastDate_thenReturnStockTransaction() {
        // Given
        final WareHouse mockWareHouseDomainModel = WareHouse.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final Stock mockStockDomainModel = Stock.builder()
                .id(UUID.randomUUID().toString())
                .build();

        final BigDecimal mockEntryAmount = BigDecimal.TEN;
        final LocalDateTime mockEntryTime = LocalDateTime.now().minusHours(3);

        StockTransactionEntity stockTransactionEntityBeforeEntryTime = StockTransactionEntity.builder()
                .afterAmount(BigDecimal.valueOf(5L))
                .build();

        // When
        Mockito.when(stockTransactionRepository.findStockTransactionEntityByDateBefore(
                mockEntryTime
        )).thenReturn(Optional.of(stockTransactionEntityBeforeEntryTime));

        // Then
        StockTransaction response = stockTransactionCreateService.createStockTransactionForStockEntry(
                mockEntryAmount,
                mockEntryTime,
                mockStockDomainModel,
                mockWareHouseDomainModel
        );

        Assertions.assertEquals(
                response.getAfterAmount(),
                mockEntryAmount.add(stockTransactionEntityBeforeEntryTime.getAfterAmount())
        );

        // Verify
        Mockito.verify(stockTransactionRepository, Mockito.times(1))
                .save(Mockito.any(StockTransactionEntity.class));

        Mockito.verify(stockTransactionRepository, Mockito.times(1))
                .updateBeforeAmountAndAfterAmountAfterSpecifiedDate(
                        mockEntryTime,
                        mockEntryAmount
                );

        Mockito.verify(stockTransactionRepository, Mockito.times(1))
                .findStockTransactionEntityByDateBefore(mockEntryTime);

    }


}
