package com.study.stockmanagementstudycase.service.wareHouseStock.impl;

import com.study.stockmanagementstudycase.common.exception.InvalidEntryAmount;
import com.study.stockmanagementstudycase.common.exception.InvalidEntryTime;
import com.study.stockmanagementstudycase.common.exception.WareHouseStockNotFoundException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockCreateService;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WareHouseStockUpdateServiceImpl implements WareHouseStockUpdateService {

    private final WareHouseStockRepository wareHouseStockRepository;
    private final WareHouseStockCreateService wareHouseStockCreateService;

    @Override
    public WareHouseStock updateWareHouseStockForStockEntry(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal entryAmount,
            final LocalDateTime entryTime
    ) {
        if (Boolean.FALSE.equals(wareHouseStockRepository
                .existsWareHouseStockEntityByStockEntityAndWareHouseEntity(
                        StockMapper.toEntity(stock),
                        WareHouseMapper.toEntity(wareHouse)
                ))
        ) {
            return wareHouseStockCreateService
                    .createWareHouseStockForStockEntry(
                            stock,
                            wareHouse,
                            entryAmount
                    );
        }

        final WareHouseStockEntity wareHouseStockEntityFromDbForStockEntry = wareHouseStockRepository
                .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                        StockMapper.toEntity(stock),
                        WareHouseMapper.toEntity(wareHouse)
                )
                .orElseThrow(WareHouseStockNotFoundException::new);

        this.checkDateTimeForWareHouseStockUpdateProcess(entryTime);

        this.checkEntryAmountForWareHouseStockUpdateProcess(entryAmount);

        this.updateWareHouseStockEntityAmountForStockEntry(
                entryAmount,
                wareHouseStockEntityFromDbForStockEntry
        );

        wareHouseStockRepository.save(wareHouseStockEntityFromDbForStockEntry);

        return WareHouseStockMapper.toDomainModel(wareHouseStockEntityFromDbForStockEntry);
    }

    @Override
    public WareHouseStock updateWareHouseStockForStockSale(
            final Stock stock,
            final WareHouse wareHouse,
            final BigDecimal saleAmount,
            final LocalDateTime saleTime
    ) {
        final WareHouseStockEntity wareHouseStockEntityFromDbForStockSale = wareHouseStockRepository
                .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                        StockMapper.toEntity(stock),
                        WareHouseMapper.toEntity(wareHouse)
                )
                .orElseThrow(WareHouseStockNotFoundException::new);

        this.checkDateTimeForWareHouseStockUpdateProcess(saleTime);

        this.checkEntryAmountForWareHouseStockUpdateProcess(saleAmount);

        this.updateWareHouseStockEntityAmountForStockSale(
                saleAmount,
                wareHouseStockEntityFromDbForStockSale
        );

        wareHouseStockRepository.save(wareHouseStockEntityFromDbForStockSale);

        return WareHouseStockMapper.toDomainModel(wareHouseStockEntityFromDbForStockSale);
    }

    private void updateWareHouseStockEntityAmountForStockEntry(
            final BigDecimal entryAmount,
            final WareHouseStockEntity wareHouseEntityForStockEntry
    ) {
        wareHouseEntityForStockEntry.setAmount(
                wareHouseEntityForStockEntry.getAmount().add(entryAmount)
        );
    }

    private void updateWareHouseStockEntityAmountForStockSale(
            final BigDecimal saleAmount,
            final WareHouseStockEntity wareHouseStockEntityForStockSale
    ) {
        wareHouseStockEntityForStockSale.setAmount(
                wareHouseStockEntityForStockSale.getAmount().subtract(saleAmount)
        );
    }

    private void checkEntryAmountForWareHouseStockUpdateProcess(
            final BigDecimal entryAmount
    ) {
        if (Boolean.FALSE.equals(entryAmount.compareTo(BigDecimal.ZERO) > 0)) {
            throw new InvalidEntryAmount();
        }
    }

    private void checkDateTimeForWareHouseStockUpdateProcess(
            final LocalDateTime processTime
    ) {
        if (processTime.isAfter(LocalDateTime.now())) {
            throw new InvalidEntryTime();
        }
    }

}
