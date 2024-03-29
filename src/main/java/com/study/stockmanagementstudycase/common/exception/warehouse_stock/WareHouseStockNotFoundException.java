package com.study.stockmanagementstudycase.common.exception.warehouse_stock;

import com.study.stockmanagementstudycase.common.exception.NotFoundException;

import java.io.Serial;

public class WareHouseStockNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 462124415267209160L;

    private static final String DEFAULT_MESSAGE =
            "The specified WareHouseStock is not found!";

    public WareHouseStockNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public WareHouseStockNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
