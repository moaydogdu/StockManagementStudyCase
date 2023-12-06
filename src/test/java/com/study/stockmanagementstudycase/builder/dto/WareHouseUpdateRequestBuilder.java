package com.study.stockmanagementstudycase.builder.dto;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;

public class WareHouseUpdateRequestBuilder extends BaseBuilder<WareHouseUpdateRequest> {
    public WareHouseUpdateRequestBuilder(
    ) {
        super(WareHouseUpdateRequest.class);
    }

    public WareHouseUpdateRequestBuilder withValidFields(
    ) {
        Faker javaFaker = new Faker();
        return this
                .withName(javaFaker.name().toString())
                .withAddress(javaFaker.address().fullAddress());
    }

    public WareHouseUpdateRequestBuilder withName(
            final String name
    ) {
        data.setName(name);
        return this;
    }

    public WareHouseUpdateRequestBuilder withAddress(
            final String address
    ) {
        data.setAddress(address);
        return this;
    }

    @Override
    public WareHouseUpdateRequest build(
    ) {
        return super.build();
    }
}
