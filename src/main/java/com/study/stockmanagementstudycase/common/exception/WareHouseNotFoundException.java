package com.study.stockmanagementstudycase.common.exception;

import java.io.Serial;

public class WareHouseNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7067959181502070426L;

    private static final String DEFAULT_MESSAGE =
            "The specified WareHouse is not found!";

    public WareHouseNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public WareHouseNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
