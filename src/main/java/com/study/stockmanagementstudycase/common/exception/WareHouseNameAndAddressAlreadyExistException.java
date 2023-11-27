package com.study.stockmanagementstudycase.common.exception;

import java.io.Serial;

public class WareHouseNameAndAddressAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -1448032964961359695L;

    private static final String DEFAULT_MESSAGE =
            "The WareHouse Name and Address already exist!";

    public WareHouseNameAndAddressAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public WareHouseNameAndAddressAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
