package com.study.stockmanagementstudycase.auth.exception;

import java.io.Serial;

public class UserStatusNotValidException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 797833221017643002L;

    private static String DEFAULT_MESSAGE = """
            User status is not valid!
            """;

    public UserStatusNotValidException(){
        super(DEFAULT_MESSAGE);
    }

    public UserStatusNotValidException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
