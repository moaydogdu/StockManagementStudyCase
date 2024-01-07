package com.study.stockmanagementstudycase.builder.dto.wareHouse;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;

public class WareHouseCreateRequestBuilder extends BaseBuilder<WareHouseCreateRequest> {

    public WareHouseCreateRequestBuilder(
    ) {
        super(WareHouseCreateRequest.class);
    }

    public WareHouseCreateRequestBuilder withValidFields(
    ) {
        Faker javaFaker = new Faker();
        return this
                .withName(javaFaker.name().toString())
                .withAddress(javaFaker.address().fullAddress());
    }

    public WareHouseCreateRequestBuilder withName(
            final String name
    ) {
        data.setName(name);
        return this;
    }

    public WareHouseCreateRequestBuilder withAddress(
            final String address
    ) {
        data.setAddress(address);
        return this;
    }

    @Override
    public WareHouseCreateRequest build(
    ) {
        return super.build();
    }

}
