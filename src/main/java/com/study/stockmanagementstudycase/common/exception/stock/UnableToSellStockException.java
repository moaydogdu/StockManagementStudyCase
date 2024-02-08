package com.study.stockmanagementstudycase.common.exception.stock;

import com.study.stockmanagementstudycase.common.exception.UnableException;

import java.io.Serial;

public class UnableToSellStockException extends UnableException {

    @Serial
    private static final long serialVersionUID = -5934502947430612867L;

    public static final String DEFAULT_MESSAGE =
            "An error occurred during the stock sale!";

    public UnableToSellStockException(){
        super(DEFAULT_MESSAGE);
    }

    public UnableToSellStockException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
