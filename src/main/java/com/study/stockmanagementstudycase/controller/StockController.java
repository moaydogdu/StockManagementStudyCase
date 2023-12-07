package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.response.StockResponse;
import com.study.stockmanagementstudycase.model.mappers.stock.StockDTOMapper;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import com.study.stockmanagementstudycase.service.stock.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
@Validated
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
    public ResponseEntity<List<StockResponse>> getStocks(){
       final List<Stock> stocks = stockService.getStocks();
        return ResponseEntity.ok(StockDTOMapper.stockResponses(stocks));

    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockResponse> getStockById(@UUID @PathVariable("stockId") final String stockId){
        final Stock stock= stockService.getStockById(stockId);
        return ResponseEntity.ok(StockDTOMapper.toStockResponse(stock));
    }
}
