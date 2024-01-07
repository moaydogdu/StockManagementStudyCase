package com.study.stockmanagementstudycase.model.dto.response.wareHouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseResponseWithStatus {
    private String id;
    private String name;
    private String address;
    private Boolean status;
}
