package com.study.stockmanagementstudycase.service.wareHouse.impl;

import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WareHouseServiceImpl implements WareHouseService {

    private final WareHouseRepository wareHouseRepository;

    @Override
    public CustomPage<WareHouse> getWareHouses(
            final CustomPagingRequest customPagingRequest
    ) {

        final Page<WareHouseEntity> wareHouseEntityListPage = wareHouseRepository
                .findWareHouseEntitiesByStatusIsTrue(customPagingRequest.toPageable());

        if (Boolean.FALSE.equals(wareHouseEntityListPage.hasContent())) {
            throw new WareHouseNotFoundException("Hiç kayıtlı depo yok!");
        }

        final List<WareHouse> wareHouseDomainModels = WareHouseMapper
                .toDomainModel(wareHouseEntityListPage.getContent());

        return CustomPage.of(
                wareHouseDomainModels,
                wareHouseEntityListPage
        );
    }

    @Override
    public WareHouse getWareHouseById(
            final String wareHouseId
    ) {
        final WareHouseEntity wareHouseEntityFromDb = wareHouseRepository
                .findById(wareHouseId)
                .orElseThrow(WareHouseNotFoundException::new);

        return WareHouseMapper
                .toDomainModel(wareHouseEntityFromDb);
    }

    @Override
    public CustomPage<WareHouse> getDeletedWareHouses(
            final CustomPagingRequest customPagingRequest
    ) {
        final Page<WareHouseEntity> deletedWareHouseEntityPage = wareHouseRepository
                .findWareHouseEntitiesByStatusIsFalse(
                        customPagingRequest.toPageable()
                );

        if (Boolean.FALSE.equals(deletedWareHouseEntityPage.hasContent())) {
            throw new WareHouseNotFoundException("Silinmiş bir depo kaydınız yok!");
        }

        final List<WareHouse> wareHouseDomainModels = WareHouseMapper
                .toDomainModel(deletedWareHouseEntityPage);

        return CustomPage.of(
                wareHouseDomainModels,
                deletedWareHouseEntityPage
        );
    }

    @Override
    public CustomPage<WareHouse> getAllWareHouses(
            final CustomPagingRequest customPagingRequest
    ) {
        final Page<WareHouseEntity> allWareHousesAsPageFromDB = wareHouseRepository
                .findAll(customPagingRequest.toPageable());

        if (Boolean.FALSE.equals(allWareHousesAsPageFromDB.hasContent())) {
            throw new WareHouseNotFoundException("Hiç kayıtlı deponuz yok!");
        }

        final List<WareHouse> wareHouseDomainModels = WareHouseMapper
                .toDomainModel(allWareHousesAsPageFromDB);

        return CustomPage.of(
                wareHouseDomainModels,
                allWareHousesAsPageFromDB
        );
    }
}
