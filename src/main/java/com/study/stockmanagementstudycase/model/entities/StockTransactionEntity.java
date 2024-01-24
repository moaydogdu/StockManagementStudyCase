package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import com.study.stockmanagementstudycase.model.enums.StockTransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents Transactions of {@link StockEntity} Entities.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "STOCK_TRANSACTION")
public class StockTransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "AMOUNT", precision = 24, scale = 4)
    private BigDecimal amount;

    @Column(name = "BEFORE_AMOUNT", precision = 24, scale = 4)
    private BigDecimal beforeAmount;

    @Column(name = "AFTER_AMOUNT", precision = 24, scale = 4)
    private BigDecimal afterAmount;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "STOCK_TRANSACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private StockTransactionType stockTransactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private StockEntity stockEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WareHouseEntity wareHouseEntity;

}
