package com.study.stockmanagementstudycase.log.service.impl;

import com.study.stockmanagementstudycase.log.entity.LogEntity;
import com.study.stockmanagementstudycase.log.repository.LogRepository;
import com.study.stockmanagementstudycase.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public void saveLogToDatabase(
            final LogEntity logEntity
    ) {
        logRepository.save(logEntity);
    }
}
