package com.study.stockmanagementstudycase.user.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private final static long serialVersionUID = -7601407921728338716L;

    private final static String DEFAULT_MESSAGE = """
            User not found!
            """;

    public UserNotFoundException(){
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
