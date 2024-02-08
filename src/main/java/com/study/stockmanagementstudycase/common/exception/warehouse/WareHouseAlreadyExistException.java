package com.study.stockmanagementstudycase.common.exception.warehouse;

import java.io.Serial;

public class WareHouseAlreadyExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1448032964961359695L;

    private static final String DEFAULT_MESSAGE =
            "The WareHouse Name and Address already exist!";

    public WareHouseAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public WareHouseAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
