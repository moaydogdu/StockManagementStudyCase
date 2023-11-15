package com.study.stockmanagementstudycase.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * This Entity Indicates How Much {@link Stock} is in {@link WareHouse}.
 */
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WAREHOUSE_STOCK")
public class WareHouseStock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "AMOUNT", scale = 24, precision = 4)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WareHouse wareHouse;

}
