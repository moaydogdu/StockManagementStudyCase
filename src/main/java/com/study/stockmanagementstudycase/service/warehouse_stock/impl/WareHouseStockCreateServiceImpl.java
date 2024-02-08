package com.study.stockmanagementstudycase.service.warehouse_stock.impl;

import com.study.stockmanagementstudycase.common.exception.warehouse_stock.UnableToCreateWareHouseStockException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.warehouse_stock.WareHouseStockCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WareHouseStockCreateServiceImpl implements WareHouseStockCreateService {

    private final WareHouseStockRepository wareHouseStockRepository;

    @Override
    public WareHouseStock createWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal entryAmount
    ) {
        // TODO : Burada amount değeri ayrıca parametre olarak alınmalı. Hatalı logic.
        this.checkEntryAmount(entryAmount);

        final WareHouseStockEntity wareHouseStockEntityForStockEntry = WareHouseStockMapper
                .mapForSaving(
                        stock,
                        wareHouse,
                        entryAmount
                );

        wareHouseStockRepository.save(wareHouseStockEntityForStockEntry);

        return WareHouseStockMapper.toDomainModel(wareHouseStockEntityForStockEntry);
    }

    private void checkEntryAmount(
            final BigDecimal entryAmount
    ) {
        if (Boolean.FALSE.equals(entryAmount.compareTo(BigDecimal.ZERO) > 0)) {
            throw new UnableToCreateWareHouseStockException("Stok giriş miktarı 0'dan büyük olmalıdır.");
        }
    }
}
