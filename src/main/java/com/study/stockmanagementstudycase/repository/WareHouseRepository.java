package com.study.stockmanagementstudycase.repository;

import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouseEntity, String> {

    boolean existsWareHouseEntitiesByNameAndAddress(
            String name,
            String address
    );

    Page<WareHouseEntity> findWareHouseEntitiesByStatusIsTrue(
            final Pageable pageable
    );

    Page<WareHouseEntity> findWareHouseEntitiesByStatusIsFalse(
            final Pageable pageable
    );
}
