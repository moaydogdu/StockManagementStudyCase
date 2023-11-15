package com.study.stockmanagementstudycase.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents Transactions of {@link Stock} Entities.
 */
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STOCK_TRANSACTION")
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "AMOUNT", scale = 24, precision = 4)
    private BigDecimal amount;

    @Column(name = "BEFORE_AMOUNT", scale = 24, precision = 4)
    private BigDecimal beforeAmount;

    @Column(name = "AFTER_AMOUNT", scale = 24, precision = 4)
    private BigDecimal afterAmount;

    @Column(name = "DATE")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WareHouse wareHouse;

}
