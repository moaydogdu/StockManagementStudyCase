package com.study.stockmanagementstudycase.security.exception.email;

import java.io.Serial;

public class EmailNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2191891062400443294L;

    private static final String DEFAULT_MESSAGE =
            "Email Not Found!";

    public EmailNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
