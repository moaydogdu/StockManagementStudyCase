package com.study.stockmanagementstudycase.builder;

import lombok.SneakyThrows;

public abstract class BaseBuilder<T> {

    public T data;

    @SneakyThrows
    public BaseBuilder(
            final Class<T> clazz
    ) {
        this.data = clazz.getDeclaredConstructor().newInstance();
    }

    public T build(
    ) {
        return data;
    }
}
