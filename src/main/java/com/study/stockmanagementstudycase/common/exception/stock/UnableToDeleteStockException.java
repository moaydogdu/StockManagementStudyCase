package com.study.stockmanagementstudycase.common.exception.stock;

import java.io.Serial;

public class UnableToDeleteStockException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2807607244632336044L;

    private static final String DEFAULT_MESSAGE =
            "Stock silme işlemi esnasında bir hata meydana geldi.";

    public UnableToDeleteStockException() {
        super(DEFAULT_MESSAGE);
    }

    public UnableToDeleteStockException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
