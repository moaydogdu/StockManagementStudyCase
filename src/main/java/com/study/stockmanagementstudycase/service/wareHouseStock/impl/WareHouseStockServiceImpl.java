package com.study.stockmanagementstudycase.service.wareHouseStock.impl;

import com.study.stockmanagementstudycase.common.exception.WareHouseStockNotFoundException;
import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingRequest;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.WareHouseStock;
import com.study.stockmanagementstudycase.model.aggregate.wareHouseStock.WareHouseStockAggregateWithWareHouse;
import com.study.stockmanagementstudycase.model.entities.StockEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WareHouseStockServiceImpl implements WareHouseStockService {

    private final WareHouseStockRepository wareHouseStockRepository;

    @Override
    public List<WareHouseStock> getWareHouseStocks() {

        final List<WareHouseStockEntity> wareHouseStockEntitiesFromDb = wareHouseStockRepository
                .findAll();

        if (Boolean.TRUE.equals(wareHouseStockEntitiesFromDb.isEmpty())) {
            throw new WareHouseStockNotFoundException();
        }

        return wareHouseStockEntitiesFromDb.stream()
                .map(WareHouseStockMapper::toDomainModel)
                .toList();
    }

    @Override
    public WareHouseStock getWareHouseStockById(
            final String wareHouseStockId
    ) {
        final WareHouseStockEntity wareHouseStockEntityFromDb = wareHouseStockRepository
                .findById(wareHouseStockId)
                .orElseThrow(WareHouseStockNotFoundException::new);

        return WareHouseStockMapper
                .toDomainModel(wareHouseStockEntityFromDb);
    }

    @Override
    public WareHouseStock getWareHouseStockByStockIdAndWareHouseId(
            final String stockId,
            final String wareHouseId
    ) {
        final WareHouseStockEntity wareHouseStockEntityFromDbByStockAndWareHouse = wareHouseStockRepository
                .findWareHouseStockEntityByStockEntityAndWareHouseEntity(
                        StockEntity.builder().id(stockId).build(),
                        WareHouseEntity.builder().id(wareHouseId).build()
                )
                .orElseThrow(WareHouseStockNotFoundException::new);

        return WareHouseStockMapper
                .toDomainModel(wareHouseStockEntityFromDbByStockAndWareHouse);
    }

    /**
     * ID değeri verilen {@link StockEntity} nesnesinin hangi depolarda kaçar adet olduğunu
     * geriye {@link WareHouseStockAggregateWithWareHouse} domain modeller içerisinde dönen metoddur.
     *
     * @param stock
     * @return
     * @author Muhammet Oguzhan AYDOGDU
     * @since 1.0.0
     */
    @Override
    public CustomPage<WareHouseStockAggregateWithWareHouse> getWareHouseStocksByStock(
            final Stock stock,
            final CustomPagingRequest customPagingRequest
    ) {
        final Page<WareHouseStockEntity> wareHouseStockEntityPageByStock = wareHouseStockRepository
                .findWareHouseStockEntitiesByStockEntity(
                        StockMapper.toEntity(stock),
                        customPagingRequest.toPageable()
                );

        if (Boolean.FALSE.equals(wareHouseStockEntityPageByStock.hasContent())) {
            throw new WareHouseStockNotFoundException("Belirtilen Stock ile ilgili depo stoğu bulunamadı.");
        }

        final List<WareHouseStockAggregateWithWareHouse> wareHouseStockAggregateWithWareHouseDomainModels =
                WareHouseStockMapper.toAggregateWithWareHouse(wareHouseStockEntityPageByStock);

        return CustomPage.of(
                wareHouseStockAggregateWithWareHouseDomainModels,
                wareHouseStockEntityPageByStock
        );
    }
}
