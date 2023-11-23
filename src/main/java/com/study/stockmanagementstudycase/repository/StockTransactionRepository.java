package com.study.stockmanagementstudycase.repository;

import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransactionEntity, String> {

}
