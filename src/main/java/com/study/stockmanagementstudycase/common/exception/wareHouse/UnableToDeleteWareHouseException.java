package com.study.stockmanagementstudycase.common.exception.wareHouse;

import java.io.Serial;

public class UnableToDeleteWareHouseException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6743189760301826388L;

    private static final String DEFAULT_MESSAGE =
            "Depo silme işlemi esnasında bir hata meydana geldi.";

    public UnableToDeleteWareHouseException(){
        super(DEFAULT_MESSAGE);
    }

    public UnableToDeleteWareHouseException(
            final String message
    ){
        super(DEFAULT_MESSAGE + " " + message);
    }
}
