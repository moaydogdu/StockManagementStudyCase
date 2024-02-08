package com.study.stockmanagementstudycase.log.repository;

import com.study.stockmanagementstudycase.log.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, String> {
}
