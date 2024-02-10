package com.study.stockmanagementstudycase.common.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {

    private String errorName;

    private String message;

    private String path;

    private Integer status;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public enum Header {

        INVALID("INVALID"),
        NOT_FOUND("NOT FOUND"),
        UNABLE("UNABLE"),
        ALREADY_EXISTS("ALREADY");

        private final String value;

        public String value() {
            return this.value;
        }

        Header(String name) {
            this.value = name;
        }
    }
}
