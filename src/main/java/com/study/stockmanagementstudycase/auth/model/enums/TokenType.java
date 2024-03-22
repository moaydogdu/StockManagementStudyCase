package com.study.stockmanagementstudycase.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TokenType {
    BEARER("Bearer");

    @Getter
    private final String value;
}
