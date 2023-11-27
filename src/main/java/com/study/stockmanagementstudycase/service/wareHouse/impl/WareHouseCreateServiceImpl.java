package com.study.stockmanagementstudycase.service.wareHouse.impl;

import com.study.stockmanagementstudycase.common.exception.WareHouseNameAndAddressAlreadyExistException;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseCreateService;
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
        if (wareHouseRepository.existsWareHouseEntitiesByNameAndAddress(name, address)) {
            throw new WareHouseNameAndAddressAlreadyExistException();
        }
    }
}
