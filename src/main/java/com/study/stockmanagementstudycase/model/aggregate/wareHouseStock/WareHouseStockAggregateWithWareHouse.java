package com.study.stockmanagementstudycase.model.aggregate.wareHouseStock;


import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
import com.study.stockmanagementstudycase.model.WareHouse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseStockAggregateWithWareHouse extends BaseDomainModel {
    private String id;
    private BigDecimal amount;
    private WareHouse wareHouse;
}
