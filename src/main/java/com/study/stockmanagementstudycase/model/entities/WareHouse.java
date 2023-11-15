package com.study.stockmanagementstudycase.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents WareHouse Entities.
 */
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
    private List<WareHouseStock> wareHouseStocks;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "wareHouse"
    )
    private List<StockTransaction> stockTransactions;

}
