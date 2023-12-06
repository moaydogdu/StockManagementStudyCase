package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.dto.response.StockGetResponse;
import com.study.stockmanagementstudycase.model.mappers.stock.StockMapper;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import com.study.stockmanagementstudycase.service.stock.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockCreateService stockCreateService;
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(
            @RequestBody @Valid final StockCreateRequest stockCreateRequest
    ) {
        stockCreateService.createStock(stockCreateRequest);

        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<StockGetResponse>> getStocks(){
        List<Stock> stocks =stockService.getStocks();
        List<StockGetResponse> stockGetRespons =StockMapper.toStockResponse(stocks);
        return ResponseEntity.ok(stockGetRespons);

    }
    @GetMapping("/{stockId}")
    public ResponseEntity<StockGetResponse> getStockById(
            @PathVariable("stockId") @UUID  String stockId
    ){
        Stock stock = stockService.getStockById(stockId);

        return ResponseEntity.ok(StockMapper.toStockResponse(stock));


    }
    @PutMapping("{stockId}")
    public ResponseEntity<Void> updateStock(
            @PathVariable("stockId") @UUID String stockId,
            @RequestBody StockUpdateRequest stockUpdateRequest
    ){
        stockService.updateStock(stockId,stockUpdateRequest);


        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{stockId}")
    public ResponseEntity<Void> deleteStockById(
            @PathVariable("stockId") @UUID final String stockId
    ){

        stockService.deleteStock(stockId);
        return ResponseEntity.ok().build();
    }

}
