package com.study.stockmanagementstudycase.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomPagingResponse<T> {

    private List<T> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElementCount;

    private Integer totalPageCount;

    public CustomPagingResponse(
            List<T> content,
            Integer pageNumber,
            Integer pageSize,
            Long totalElementCount, Integer totalPageCount
    ) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElementCount = totalElementCount;
        this.totalPageCount = totalPageCount;
    }


    public static class CustomPagingResponseBuilder<T> {
        public <C> CustomPagingResponse.CustomPagingResponseBuilder<T> of(
                final CustomPage<C> customPage
        ) {
            return CustomPagingResponse.<T>builder()
                    .pageNumber(customPage.getPageNumber())
                    .pageSize(customPage.getPageSize())
                    .totalElementCount(customPage.getTotalElementCount())
                    .totalPageCount(customPage.getTotalPageCount());
        }

        public CustomPagingResponse.CustomPagingResponseBuilder<T> content(
                final List<T> _content
        ) {
            this.content = _content;
            return CustomPagingResponse.<T>builder();
        }


        public CustomPagingResponse<T> build() {
            return new CustomPagingResponse<T>(this.content, this.pageNumber, this.pageSize, this.totalElementCount, this.totalPageCount);
        }
    }
}
