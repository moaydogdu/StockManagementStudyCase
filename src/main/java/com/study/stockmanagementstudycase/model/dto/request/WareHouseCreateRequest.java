package com.study.stockmanagementstudycase.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseCreateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;

}
