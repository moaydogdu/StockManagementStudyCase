package com.study.stockmanagementstudycase.common.exception.stock;

import com.study.stockmanagementstudycase.common.exception.NotFoundException;

import java.io.Serial;

public class StockNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -9053303564603191385L;

    private static final String DEFAULT_MESSAGE =
            "The specified Stock is not found!";

    public StockNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public StockNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
