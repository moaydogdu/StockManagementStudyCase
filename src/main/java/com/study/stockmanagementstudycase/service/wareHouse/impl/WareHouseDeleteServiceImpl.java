package com.study.stockmanagementstudycase.service.wareHouse.impl;

import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WareHouseDeleteServiceImpl implements WareHouseDeleteService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public void deleteWareHouse(
            final String wareHouseId
    ) {
        wareHouseRepository
                .findById(wareHouseId)
                .orElseThrow(() -> new WareHouseNotFoundException("WareHouse not found"));

        wareHouseRepository.deleteById(wareHouseId);
    }

}
