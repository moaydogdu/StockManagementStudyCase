package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseAlreadyExistException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.warehouse.WareHouseCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WareHouseCreateServiceImpl implements WareHouseCreateService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public WareHouse createWareHouse(
            final WareHouseCreateRequest request
    ) {
        this.checkWareHouseNameAndAddressUniqueness(
                request.getName(),
                request.getAddress()
        );

        final WareHouseEntity wareHouseEntityToBeCreate = WareHouseMapper
                .mapForSaving(request);

        wareHouseRepository.save(wareHouseEntityToBeCreate);

        return WareHouseMapper.toDomainModel(wareHouseEntityToBeCreate);
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
