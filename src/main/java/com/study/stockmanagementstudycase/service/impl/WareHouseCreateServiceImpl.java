package com.study.stockmanagementstudycase.service.impl;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.WareHouseCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WareHouseCreateServiceImpl implements WareHouseCreateService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public void createWareHouse(
            final WareHouseCreateRequest request
    ) {
        this.checkWareHouseNameAndAddressUniqueness(
                request.getName(),
                request.getAddress()
        );

        final WareHouseEntity wareHouseEntityToBeCreate = WareHouseMapper
                .mapForSaving(request);

        wareHouseRepository.save(wareHouseEntityToBeCreate);
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
