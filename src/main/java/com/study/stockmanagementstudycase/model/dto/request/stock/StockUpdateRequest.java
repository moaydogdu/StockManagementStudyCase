package com.study.stockmanagementstudycase.model.dto.request.stock;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateRequest {

    @NotBlank()
    private String name;

    @DecimalMin(value = "0.0001", message = "Price 0'dan büyük olmalıdır.")
    private BigDecimal price;

}
