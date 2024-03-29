package com.study.stockmanagementstudycase.service.warehouse.impl;

import com.study.stockmanagementstudycase.common.exception.warehouse.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.common.exception.warehouse.UnableToDeleteWareHouseException;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.warehouse.WareHouseDeleteService;
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
        final WareHouseEntity wareHouseEntityToBeDelete = wareHouseRepository
                .findById(wareHouseId)
                .orElseThrow(WareHouseNotFoundException::new);

        if (Boolean.FALSE.equals(wareHouseEntityToBeDelete.getStatus())){
            throw new UnableToDeleteWareHouseException("Depo zaten silinmiş.");
        }

        wareHouseEntityToBeDelete.setStatus(false);

        wareHouseRepository.save(wareHouseEntityToBeDelete);
    }

}
