package com.study.stockmanagementstudycase.common.exception;

import java.io.Serial;

public class WareHouseEntityNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -7067959181502070424L;

    private static final String DEFAULT_MESSAGE =
            "The specified WareHouse is not found!";

    public WareHouseEntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public WareHouseEntityNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
