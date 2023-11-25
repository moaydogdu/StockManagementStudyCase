package com.study.stockmanagementstudycase.model;

import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
import com.study.stockmanagementstudycase.model.enums.StockTransactionType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockTransaction extends BaseDomainModel {
    private String id;
    private BigDecimal amount;
    private BigDecimal beforeAmount;
    private BigDecimal afterAmount;
    private LocalDateTime date;
    private StockTransactionType stockTransactionType;
    private String stockEntityId;
    private String wareHouseEntityId;
}
