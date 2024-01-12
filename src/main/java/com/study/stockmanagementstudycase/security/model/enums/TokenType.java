package com.study.stockmanagementstudycase.security.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TokenType {
    BEARER("Bearer");

    @Getter
    private final String value;
}
