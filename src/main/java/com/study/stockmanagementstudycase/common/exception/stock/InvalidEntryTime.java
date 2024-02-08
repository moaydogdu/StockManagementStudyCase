package com.study.stockmanagementstudycase.common.exception.stock;

import com.study.stockmanagementstudycase.common.exception.InvalidException;

import java.io.Serial;

public class InvalidEntryTime extends InvalidException {

    @Serial
    private static final long serialVersionUID = -166975915077460712L;

    private static final String DEFAULT_MESSAGE =
            "Invalid Entry Time!";

    public InvalidEntryTime() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidEntryTime(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
