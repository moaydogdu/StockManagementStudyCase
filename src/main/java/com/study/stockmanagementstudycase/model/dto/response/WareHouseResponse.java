package com.study.stockmanagementstudycase.model.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseResponse {

    private String id;

    private String name;

    private String address;

}
