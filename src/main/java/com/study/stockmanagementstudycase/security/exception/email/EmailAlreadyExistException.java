package com.study.stockmanagementstudycase.security.exception.email;

import java.io.Serial;

public class EmailAlreadyExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6291891062400443294L;

    private static final String DEFAULT_MESSAGE =
            "The Email Address already exist!";

    public EmailAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
