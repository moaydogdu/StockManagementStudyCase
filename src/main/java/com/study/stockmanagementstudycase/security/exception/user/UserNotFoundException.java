package com.study.stockmanagementstudycase.security.exception.user;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4127052933790053523L;

    private static final String DEFAULT_MESSAGE =
            "User Not Found!";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
