package com.study.stockmanagementstudycase.common.exception;

import java.io.Serial;
import java.io.Serializable;

public class WareHouseEntityNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -7067959181502070423L;

    private static final String DEFAULT_MESSAGE =
            "The specified WareHouseEntity is not found!";

    public WareHouseEntityNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
