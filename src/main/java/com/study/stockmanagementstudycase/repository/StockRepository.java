package com.study.stockmanagementstudycase.repository;

import com.study.stockmanagementstudycase.model.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
