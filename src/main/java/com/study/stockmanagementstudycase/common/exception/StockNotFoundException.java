package com.study.stockmanagementstudycase.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class StockNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -7067959181502070423L;

    private static final String DEFAULT_MESSAGE =
            "The specified Stock is not found!";

    public StockNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
