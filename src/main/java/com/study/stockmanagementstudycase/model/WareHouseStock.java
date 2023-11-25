package com.study.stockmanagementstudycase.model;


import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
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
public class WareHouseStock extends BaseDomainModel {

    private String id;
    private BigDecimal amount;
    private String stockEntityId;
    private String wareHouseEntityId;

}
