package com.study.stockmanagementstudycase.builder.entity;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.common.model.util.CustomFakeDataGenerator;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.enums.UnitType;

import java.math.BigDecimal;
import java.util.UUID;


public class StockEntityBuilder extends BaseBuilder<StockEntity> {

    public StockEntityBuilder(
    ) {
        super(StockEntity.class);
    }

    public StockEntityBuilder withValidFields(
    ) {
        Faker javaFaker = new Faker();
        return this
                .withId(UUID.randomUUID().toString())
                .withName(javaFaker.name().firstName().toString())
                .withPrice(CustomFakeDataGenerator.generateRandomPrice())
                .withAmount(CustomFakeDataGenerator.generateRandomAmount())
                .withUnitType(CustomFakeDataGenerator.generateRandomUnitType());
    }

    public StockEntityBuilder withId(
            final String id
    ) {
        data.setId(id);
        return this;
    }

    public StockEntityBuilder withName(
            final String name
    ) {
        data.setName(name);
        return this;
    }

    public StockEntityBuilder withPrice(
            final BigDecimal price
    ) {
        data.setPrice(price);
        return this;
    }

    public StockEntityBuilder withAmount(
            final BigDecimal amount
    ) {
        data.setAmount(amount);
        return this;
    }

    public StockEntityBuilder withUnitType(
            final UnitType unitType
    ) {
        data.setUnitType(unitType);
        return this;
    }


    @Override
    public StockEntity build(
    ) {
        return super.build();
    }

}
