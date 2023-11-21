package com.study.stockmanagementstudycase.service.impl;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.WareHouseUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WareHouseUpdateServiceImpl implements WareHouseUpdateService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public void updateWareHouse(
            final WareHouseUpdateRequest updateRequest,
            final String warehouseId
    ) {
        final WareHouseEntity wareHouseEntityFromDb = wareHouseRepository.
                findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("WareHouseEntity not found"));

        this.checkWareHouseNameAndAddressUniqueness(
                updateRequest.getName(),
                updateRequest.getAddress()
        );

        WareHouseMapper.mapForUpdating(updateRequest, wareHouseEntityFromDb);

        wareHouseRepository.save(wareHouseEntityFromDb);

    }

    private void checkWareHouseNameAndAddressUniqueness(
            final String name,
            final String address
    ) {
        if (wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(name, address)) {
            throw new RuntimeException("WareHouse with given name or address is already exist");
        }
    }
}
