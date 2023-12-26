package com.study.stockmanagementstudycase.model.dto.request.stock;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
