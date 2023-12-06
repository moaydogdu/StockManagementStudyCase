package com.study.stockmanagementstudycase.common.exception;

import java.io.Serial;

public class StockAlreadyExistexception extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -2675930856483765098L;

    private static final String DEFAULT_MESSAGE =
            "The Stock Name  already exist!";

    public StockAlreadyExistexception() {
        super(DEFAULT_MESSAGE);
    }

    public StockAlreadyExistexception(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
