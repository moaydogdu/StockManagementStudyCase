package com.study.stockmanagementstudycase.common.exception.stock;

import java.io.Serial;

public class UnableToSellStockException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -5934502947430612867L;

    public static final String DEFAULT_MESSAGE =
            "Stok satışı sırasında bir hata meydana geldi.";

    public UnableToSellStockException(){
        super(DEFAULT_MESSAGE);
    }

    public UnableToSellStockException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
