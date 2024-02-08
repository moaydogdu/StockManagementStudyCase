package com.study.stockmanagementstudycase.common.exception;

import org.springframework.http.HttpStatus;

public abstract class InvalidException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    protected InvalidException(
            final String message
    ) {
        super(message);
    }
}
