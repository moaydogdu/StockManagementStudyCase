package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import com.study.stockmanagementstudycase.model.enums.StockTransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents Transactions of {@link Stock} Entities.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "STOCK_TRANSACTION")
public class StockTransaction extends BaseEntity {

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

    @Column(name = "STOCK_TRANSACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private StockTransactionType stockTransactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WareHouse wareHouse;

}
