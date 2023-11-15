package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import com.study.stockmanagementstudycase.model.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents Stock Entities.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "STOCK")
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE", scale = 24, precision = 4)
    private BigDecimal price;

    @Column(name = "AMOUNT", scale = 24, precision = 4)
    private BigDecimal amount;

    @Column(name = "UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stock"
    )
    private List<StockTransaction> stockTransactions;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stock"
    )
    private List<WareHouseStock> wareHouseStocks;

}
