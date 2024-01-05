package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import com.study.stockmanagementstudycase.model.enums.UnitType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class StockEntity extends BaseEntity {

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

    /**
     * if status is {@code true}, that means is active. <br>
     * else status is {@code false}, that means this {@link StockEntity} is deleted.
     */
    @Column(name = "STATUS")
    @Builder.Default
    private Boolean status = true;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stockEntity"
    )
    private List<StockTransactionEntity> stockTransactionEntities;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stockEntity"
    )
    private List<WareHouseStockEntity> wareHouseStockEntities;

}
