package com.study.stockmanagementstudycase.user.exception;

import java.io.Serial;

public class UserAlreadyExistException extends RuntimeException{

    @Serial
    private final static long serialVersionUID = -6003193146585190592L;

    private final static String DEFAULT_MESSAGE = """
            User already exist!
            """;

    public UserAlreadyExistException(){
        super(DEFAULT_MESSAGE);
    }

    public UserAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
