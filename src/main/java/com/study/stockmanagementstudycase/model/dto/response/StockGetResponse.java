package com.study.stockmanagementstudycase.model.dto.response;

import com.study.stockmanagementstudycase.model.enums.UnitType;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockGetResponse {
    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private UnitType unitType;

}
