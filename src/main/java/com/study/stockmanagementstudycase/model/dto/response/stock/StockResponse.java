package com.study.stockmanagementstudycase.model.dto.response.stock;

import com.study.stockmanagementstudycase.model.enums.UnitType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private UnitType unitType;

}
