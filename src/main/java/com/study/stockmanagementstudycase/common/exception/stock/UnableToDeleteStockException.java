package com.study.stockmanagementstudycase.common.exception.stock;

import com.study.stockmanagementstudycase.common.exception.UnableException;

import java.io.Serial;

public class UnableToDeleteStockException extends UnableException {

    @Serial
    private static final long serialVersionUID = -2807607244632336044L;

    private static final String DEFAULT_MESSAGE =
            "An error occurred during the stock deletion!";

    public UnableToDeleteStockException() {
        super(DEFAULT_MESSAGE);
    }

    public UnableToDeleteStockException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
