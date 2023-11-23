package com.study.stockmanagementstudycase.model;

import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouse {

    private String id;

    private String name;

    private String address;

}
