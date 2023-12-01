package com.study.stockmanagementstudycase.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomSubError {

    private String message;

    private String field;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
}
