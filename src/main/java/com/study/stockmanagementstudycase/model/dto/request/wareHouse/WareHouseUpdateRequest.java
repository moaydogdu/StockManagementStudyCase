package com.study.stockmanagementstudycase.model.dto.request.wareHouse;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseUpdateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;

}
