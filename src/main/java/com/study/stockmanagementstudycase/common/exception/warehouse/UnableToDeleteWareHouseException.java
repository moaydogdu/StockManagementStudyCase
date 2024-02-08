package com.study.stockmanagementstudycase.common.exception.warehouse;

import com.study.stockmanagementstudycase.common.exception.UnableException;

import java.io.Serial;

public class UnableToDeleteWareHouseException extends UnableException {

    @Serial
    private static final long serialVersionUID = 6743189760301826388L;

    private static final String DEFAULT_MESSAGE =
            "An error occurred during the warehouse deletion!";

    public UnableToDeleteWareHouseException(){
        super(DEFAULT_MESSAGE);
    }

    public UnableToDeleteWareHouseException(
            final String message
    ){
        super(DEFAULT_MESSAGE + " " + message);
    }
}
