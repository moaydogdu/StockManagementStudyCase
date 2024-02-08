package com.study.stockmanagementstudycase.common.exception.stock;

import com.study.stockmanagementstudycase.common.exception.InvalidException;

import java.io.Serial;

public class InvalidEntryAmount extends InvalidException {

    @Serial
    private static final long serialVersionUID = -602023018597079935L;

    private static final String DEFAULT_MESSAGE =
            "Invalid Entry Amount!";

    public InvalidEntryAmount() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidEntryAmount(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
