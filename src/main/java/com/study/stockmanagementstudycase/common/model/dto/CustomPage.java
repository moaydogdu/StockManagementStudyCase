package com.study.stockmanagementstudycase.common.model.dto;

import com.study.stockmanagementstudycase.model.WareHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPage<T> {
    private List<T> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElementCount;

    private Integer totalPageCount;


    public static <C, X> CustomPage<C> of(
            final List<C> domainModels,
            final Page<X> page
    ) {
        return CustomPage.<C>builder()
                .content(domainModels)
                .pageNumber(page.getNumber() + 1)
                .pageSize(page.getSize())
                .totalPageCount(page.getTotalPages())
                .totalElementCount(page.getTotalElements())
                .build();
    }
}
