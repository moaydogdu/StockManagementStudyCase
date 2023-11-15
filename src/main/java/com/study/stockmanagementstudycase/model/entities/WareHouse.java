package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents WareHouse Entities.
 */
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "WAREHOUSE")
public class WareHouse extends BaseEntity {

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
