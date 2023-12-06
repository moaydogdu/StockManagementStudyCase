package com.study.stockmanagementstudycase.service.stock.impl;

import com.study.stockmanagementstudycase.common.exception.StockAlreadyExistexception;
import com.study.stockmanagementstudycase.common.exception.StockNotFoundException;

import com.study.stockmanagementstudycase.common.exception.WareHouseNotFoundException;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.entities.StockEntity;

import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.model.entities.WareHouseStockEntity;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.repository.StockRepository;

import com.study.stockmanagementstudycase.repository.WareHouseRepository;
import com.study.stockmanagementstudycase.repository.WareHouseStockRepository;
import com.study.stockmanagementstudycase.service.stock.StockService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final WareHouseStockRepository wareHouseStockRepository;
    private final WareHouseRepository wareHouseRepository;
    @Override
    public List<Stock> getStocks() {
        List<StockEntity> stockEntities = stockRepository.findAll();

        return StockMapper.toDomainModel(stockEntities);


    }

    @Override
    public Stock getStockById(String stockId) {
        final StockEntity stockEntity = stockRepository.findById(stockId).orElseThrow(StockNotFoundException::new);


        return StockMapper.toDomainModel(stockEntity);



    }

    @Override
    public void updateStock(String stockId, StockUpdateRequest stockUpdateRequest) {
     final  StockEntity stockEntity = stockRepository.findById(stockId).orElseThrow(StockNotFoundException::new);
           this.checkStockNameUniqueness(stockUpdateRequest.getName());

            Stock stock = StockMapper.UpdateforDomainModel(stockEntity,stockUpdateRequest);

            this.stockRepository.save(stockEntity);



    }

    @Override
    public void deleteStock(String stockId) {
      StockEntity stockEntity=  this.stockRepository.findById(stockId).orElseThrow(StockNotFoundException::new);
        wareHouseStockRepository.findWareHouseStockEntitiesByStockEntity(stockEntity);
        this.checkWareHouseExist(stockEntity);

    }

    private void checkStockNameUniqueness(String name){
         if (Boolean.TRUE.equals(stockRepository
                .existsByName(name))) {
            throw new StockAlreadyExistexception();
        }
    }
    private void checkWareHouseExist(StockEntity stockEntity){

        //TODO:Muhammete sor burayı DDD ye uygun yap
        Optional<WareHouseStockEntity> wareHouseStock=wareHouseStockRepository.findWareHouseStockEntitiesByStockEntity(stockEntity);

         if(wareHouseStock.isPresent()){
             WareHouseStockEntity wareHouseStockEntity = wareHouseStock.get();
             Optional<WareHouseEntity> wareHouseEntity = wareHouseRepository.findById(wareHouseStockEntity.getId());
             if (wareHouseEntity.isPresent()){
                 WareHouseEntity wareHouseEntity1=wareHouseEntity.get();
                 wareHouseEntity1.getWareHouseStockEntities().remove(wareHouseStockEntity);
                 wareHouseStockRepository.delete(wareHouseStockEntity);
             }


        }

    }



}
