package com.study.stockmanagementstudycase.common.model.util;

import com.github.javafaker.Faker;
import com.study.stockmanagementstudycase.model.enums.UnitType;

import java.math.BigDecimal;

public class CustomFakeDataGenerator {

    public static BigDecimal generateRandomPrice() {
        Faker faker = new Faker();

        int dollars = faker.number().numberBetween(0, 100000);
        int cents = faker.number().numberBetween(0, 99);

        return new BigDecimal(String.format("%d.%02d", dollars, cents));
    }

    public static BigDecimal generateRandomAmount() {
        Faker faker = new Faker();

        int randomAmount = faker.number().numberBetween(0, 100);

        return new BigDecimal(randomAmount);
    }

    public static UnitType generateRandomUnitType() {
        Faker faker = new Faker();

        int randomNumber = faker.number().numberBetween(1,
                UnitType.values().length);

        return UnitType.values()[randomNumber];
    }
}
