package com.study.stockmanagementstudycase.model;

import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Domain model of {@link StockEntity}
 */

@SuperBuilder
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends BaseDomainModel {
    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private UnitType unitType;
}
