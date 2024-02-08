package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseAlreadyExistException;
import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.warehouse.WareHouseUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WareHouseUpdateServiceImpl implements WareHouseUpdateService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public void updateWareHouse(
            final WareHouseUpdateRequest updateRequest,
            final String warehouseId
    ) {

        this.checkWareHouseNameAndAddressUniqueness(
                updateRequest.getName(),
                updateRequest.getAddress()
        );

        final WareHouseEntity wareHouseEntityFromDb = wareHouseRepository.
                findById(warehouseId)
                .orElseThrow(WareHouseNotFoundException::new);

        WareHouseMapper.mapForUpdating(updateRequest, wareHouseEntityFromDb);

        wareHouseRepository.save(wareHouseEntityFromDb);

    }

    private void checkWareHouseNameAndAddressUniqueness(
            final String name,
            final String address
    ) {
        if (Boolean.TRUE.equals(wareHouseRepository
                .existsWareHouseEntitiesByNameAndAddress(name, address))) {
            throw new WareHouseAlreadyExistException("The WareHouse Name and Address already exist!");
        }
    }
}
