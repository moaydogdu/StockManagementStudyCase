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
            String id
    ) {
        WareHouseEntity wareHouseEntity = wareHouseRepository.
                findById(id)
                .orElseThrow(() -> new RuntimeException("WareHouseEntity not found"));

        this.checkWareHouseNameAndAddressUniqueness(
                updateRequest.getName(),
                updateRequest.getAddress()
        );

        final WareHouseEntity wareHouseEntityToBeUpdate = WareHouseMapper
                .mapForUpdating(updateRequest, id);

        wareHouseRepository.save(wareHouseEntityToBeUpdate);
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
