package com.study.stockmanagementstudycase.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WAREHOUSE")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "wareHouse"
    )
    @JoinColumn(name = "WAREHOUSE_STOCK")
    private List<WareHouseStock> wareHouseStocks;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "wareHouse"
    )
    @JoinColumn(name = "STOCK_TRANSACTION")
    private List<StockTransaction> stockTransactions;

}