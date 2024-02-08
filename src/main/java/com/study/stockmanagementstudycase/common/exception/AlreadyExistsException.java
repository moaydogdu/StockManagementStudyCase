package com.study.stockmanagementstudycase.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AlreadyExistsException extends RuntimeException {

    public static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

    protected AlreadyExistsException(
            final String message
    ) {
        super(message);
    }
}
