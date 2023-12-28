package com.study.stockmanagementstudycase.common.model.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomPaging {

    @Min(value = 1, message = "PageNumber alanı minimum 1 değerine sahip olmalıdır.")
    private Integer pageNumber;

    private Integer pageSize;

    public Integer getPageNumber() {
        return pageNumber - 1;
    }

}
