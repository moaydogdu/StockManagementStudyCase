package com.study.stockmanagementstudycase.model.dto.response.wareHouseStock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseStockResponse {
    public String wareHouseId;
    public String wareHouseName;
    public BigDecimal amount;
}


