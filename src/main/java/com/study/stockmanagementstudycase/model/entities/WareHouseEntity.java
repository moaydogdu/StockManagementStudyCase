package com.study.stockmanagementstudycase.model.entities;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class WareHouseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    /**
     * if status is {@code true}, that means is active. <br>
     * else status is {@code false}, that means this {@link WareHouseEntity} is deleted.
     */
    @Column(name = "STATUS")
    @Builder.Default
    private Boolean status = true;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "wareHouseEntity"
    )
    private List<WareHouseStockEntity> wareHouseStockEntities;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "wareHouseEntity"
    )
    private List<StockTransactionEntity> stockTransactionEntities;

}
