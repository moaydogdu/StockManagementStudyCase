package com.study.stockmanagementstudycase.repository;

import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WareHouseStockRepository extends JpaRepository<WareHouseStockEntity, String> {

    Optional<WareHouseStockEntity> findWareHouseStockEntityByStockEntityAndWareHouseEntity(
            final StockEntity stockEntity,
            final WareHouseEntity wareHouseEntity
    );

    boolean existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
            final StockEntity stockEntity,
            final WareHouseEntity wareHouseEntity
    );
}
