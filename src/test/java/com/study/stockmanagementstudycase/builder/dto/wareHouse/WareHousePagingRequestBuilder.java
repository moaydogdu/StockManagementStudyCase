package com.study.stockmanagementstudycase.builder.dto.wareHouse;

import com.study.stockmanagementstudycase.builder.BaseBuilder;
import com.study.stockmanagementstudycase.builder.dto.CustomPagingBuilder;
import com.study.stockmanagementstudycase.common.model.dto.CustomPaging;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHousePagingRequest;

public class WareHousePagingRequestBuilder extends BaseBuilder<WareHousePagingRequest> {
    public WareHousePagingRequestBuilder() {
        super(WareHousePagingRequest.class);
    }

    public WareHousePagingRequestBuilder withValidFields() {
        return this
                .withPagination(new CustomPagingBuilder().withValidFields().build());
    }

    public WareHousePagingRequestBuilder withPagination(
            final CustomPaging customPaging
    ) {
        data.setPagination(customPaging);
        return this;
    }

    @Override
    public WareHousePagingRequest build() {
        return super.build();
    }
}
