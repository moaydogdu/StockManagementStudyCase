package com.study.stockmanagementstudycase.common.exception.warehouse;

import com.study.stockmanagementstudycase.common.exception.NotFoundException;

import java.io.Serial;

public class WareHouseNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 2810295268219378487L;

    private static final String DEFAULT_MESSAGE =
            "The specified WareHouse is not found!";

    public WareHouseNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public WareHouseNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
