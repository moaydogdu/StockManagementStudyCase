package com.study.stockmanagementstudycase.repository;

import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouseEntity, String> {

    /**
     * Checks WareHouseEntity in database by name or address field.
     * If entity exists returns true
     * @param name
     * @param address
     * @return
     */
    boolean existsWareHouseEntitiesByNameAndAddress(
            String name,
            String address
    );
}
