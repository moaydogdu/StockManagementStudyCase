package com.study.stockmanagementstudycase.builder.dto.stock;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.common.model.util.CustomFakeDataGenerator;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;

import java.math.BigDecimal;

public class StockUpdateRequestBuilder extends BaseBuilder<StockUpdateRequest> {
    public StockUpdateRequestBuilder(
    ) {
        super(StockUpdateRequest.class);
    }

    public StockUpdateRequestBuilder withValidFields(
    ) {
        Faker javaFaker = new Faker();
        return this
                .withName(javaFaker.stock().toString())
                .withPrice(CustomFakeDataGenerator.generateRandomPrice());
    }

    public StockUpdateRequestBuilder withName(
            final String name
    ) {
        data.setName(name);
        return this;
    }

    public StockUpdateRequestBuilder withPrice(
            final BigDecimal price
    ) {
        data.setPrice(price);
        return this;
    }

    @Override
    public StockUpdateRequest build(
    ) {
        return super.build();
    }
}
