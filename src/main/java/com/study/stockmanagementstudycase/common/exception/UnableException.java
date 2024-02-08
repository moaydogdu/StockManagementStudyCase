package com.study.stockmanagementstudycase.common.exception;

import org.springframework.http.HttpStatus;

public abstract class UnableException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    protected UnableException(
            final String message
    ) {
        super(message);
    }
}

