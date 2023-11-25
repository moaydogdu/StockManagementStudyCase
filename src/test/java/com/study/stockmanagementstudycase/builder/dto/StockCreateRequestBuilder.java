package com.study.stockmanagementstudycase.builder.dto;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.common.model.util.CustomFakeDataGenerator;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.enums.UnitType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class StockCreateRequestBuilder extends BaseBuilder<StockCreateRequest> {
    public StockCreateRequestBuilder(
    ) {
        super(StockCreateRequest.class);
    }

    public StockCreateRequestBuilder withValidFields(
    ) {
        Faker javaFaker = new Faker();
        return this
                .withName(javaFaker.stock().toString())
                .withPrice(CustomFakeDataGenerator.generateRandomPrice())
                .withAmount(CustomFakeDataGenerator.generateRandomAmount())
                .withDateTime(LocalDateTime.now())
                .withUnitType(CustomFakeDataGenerator.generateRandomUnitType())
                .withWareHouseId(UUID.randomUUID().toString());
    }

    public StockCreateRequestBuilder withName(
            final String name
    ) {
        data.setName(name);
        return this;
    }

    public StockCreateRequestBuilder withPrice(
            final BigDecimal price
    ) {
        data.setPrice(price);
        return this;
    }

    public StockCreateRequestBuilder withAmount(
            final BigDecimal amount
    ) {
        data.setAmount(amount);
        return this;
    }

    public StockCreateRequestBuilder withDateTime(
            final LocalDateTime dateTime
    ) {
        data.setDateTime(dateTime);
        return this;
    }

    public StockCreateRequestBuilder withUnitType(
            final UnitType unitType
    ) {
        data.setUnitType(unitType);
        return this;
    }

    public StockCreateRequestBuilder withWareHouseId(
            final String wareHouseId
    ) {
        data.setWareHouseId(wareHouseId);
        return this;
    }

    public StockCreateRequestBuilder withoutAmount(
    ) {
        data.setAmount(null);
        return this;
    }

    @Override
    public StockCreateRequest build(
    ) {
        return super.build();
    }
}
