package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * This Entity Indicates How Much {@link StockEntity} is in {@link WareHouseEntity}.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "WAREHOUSE_STOCK")
public class WareHouseStockEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "AMOUNT", scale = 24, precision = 4)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private StockEntity stockEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WareHouseEntity wareHouseEntity;

}
