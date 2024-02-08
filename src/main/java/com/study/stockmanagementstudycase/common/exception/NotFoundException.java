package com.study.stockmanagementstudycase.common.exception;

import org.springframework.http.HttpStatus;

public abstract class NotFoundException extends RuntimeException{

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    protected NotFoundException(
            final String message
    ) {
        super(message);
    }
}
