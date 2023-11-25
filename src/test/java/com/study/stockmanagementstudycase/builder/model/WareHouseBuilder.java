package com.study.stockmanagementstudycase.builder.model;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.model.WareHouse;

import java.util.UUID;

public class WareHouseBuilder extends BaseBuilder<WareHouse> {
    public WareHouseBuilder(
    ) {
        super(WareHouse.class);
    }

    public WareHouseBuilder withValidFields(
    ) {
        Faker javaFaker = new Faker();
        return this
                .withId(UUID.randomUUID().toString())
                .withName(javaFaker.name().firstName().toString())
                .withAddress(javaFaker.address().fullAddress().toString());
    }

    public WareHouseBuilder withId(
            final String id
    ) {
        data.setId(id);
        return this;
    }

    public WareHouseBuilder withName(
            final String name
    ) {
        data.setName(name);
        return this;
    }

    public WareHouseBuilder withAddress(
            final String address
    ) {
        data.setAddress(address);
        return this;
    }


    @Override
    public WareHouse build() {
        return super.build();
    }
}
