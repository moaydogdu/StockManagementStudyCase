package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.model.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STOCK")
public class Stock {

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

    @Column(name = "UNITTYPE")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stock"
    )
    @JoinColumn(name = "STOCK_TRANSACTION")
    private List<StockTransaction> stockTransactions;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stock"
    )
    @JoinColumn(name = "WAREHOUSE_STOCK")
    private List<WareHouseStock> wareHouseStocks;

}