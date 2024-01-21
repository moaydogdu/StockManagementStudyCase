package com.study.stockmanagementstudycase.security.exception.token;

import java.io.Serial;

public class TokenInterferedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6291891062400443294L;

    private static final String DEFAULT_MESSAGE =
            "The Token was Interfered with!";

    public TokenInterferedException() {
        super(DEFAULT_MESSAGE);
    }

    public TokenInterferedException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
