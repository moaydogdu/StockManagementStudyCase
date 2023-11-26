package com.study.stockmanagementstudycase.common.exception;

import java.io.Serial;
import java.io.Serializable;

public class WareHouseNameAndAddressAlreadyExistException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -7067959181502070423L;

    private static final String DEFAULT_MESSAGE =
            "The WareHouse Name and Address already exist!";

    public WareHouseNameAndAddressAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
