package com.study.stockmanagementstudycase.auth.exception;

import java.io.Serial;

public class PasswordNotValidException extends RuntimeException{

    @Serial
    private final static long serialVersionUID = -4287638167867746032L;

    private final static String DEFAULT_MESSAGE = """
            Password not valid!
            """;

    public PasswordNotValidException(){
        super(DEFAULT_MESSAGE);
    }

    public PasswordNotValidException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
