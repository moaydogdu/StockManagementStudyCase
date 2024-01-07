package com.study.stockmanagementstudycase.builder.dto.stock;

import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.builder.dto.CustomPagingBuilder;
import com.study.stockmanagementstudycase.common.model.dto.CustomPaging;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockPagingRequest;

public class StockPagingRequestBuilder extends BaseBuilder<StockPagingRequest> {
    public StockPagingRequestBuilder() {
        super(StockPagingRequest.class);
    }

    public StockPagingRequestBuilder withValidFields() {
        return this
                .withPagination(new CustomPagingBuilder().withValidFields().build());
    }

    public StockPagingRequestBuilder withPagination(
            final CustomPaging customPaging
    ) {
        data.setPagination(customPaging);
        return this;
    }

    @Override
    public StockPagingRequest build() {
        return super.build();
    }
}
