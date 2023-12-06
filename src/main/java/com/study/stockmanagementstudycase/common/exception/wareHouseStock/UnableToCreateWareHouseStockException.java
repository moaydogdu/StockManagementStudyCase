package com.study.stockmanagementstudycase.common.exception.wareHouseStock;

import java.io.Serial;

public class UnableToCreateWareHouseStockException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6692806872818052178L;

    private static final String DEFAULT_MESSAGE =
            "Unable to create WareHouseStock!";

    public UnableToCreateWareHouseStockException() {
        super(DEFAULT_MESSAGE);
    }

    public UnableToCreateWareHouseStockException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
