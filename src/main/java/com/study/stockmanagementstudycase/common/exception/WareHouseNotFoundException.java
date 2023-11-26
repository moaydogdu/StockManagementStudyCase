package com.study.stockmanagementstudycase.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WareHouseNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public WareHouseNotFoundException(final String message) {
        super(message);
    }

}
