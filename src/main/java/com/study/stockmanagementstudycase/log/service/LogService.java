package com.study.stockmanagementstudycase.log.service;

import com.study.stockmanagementstudycase.log.entity.LogEntity;

public interface LogService {

    void saveLogToDatabase(
            final LogEntity logEntity
    );
}
