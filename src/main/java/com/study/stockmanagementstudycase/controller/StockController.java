package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockEntryRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockSaleRequest;
import com.study.stockmanagementstudycase.model.dto.response.StockResponse;
import com.study.stockmanagementstudycase.model.mappers.stock.StockDTOMapper;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import com.study.stockmanagementstudycase.service.stock.StockEntryService;
import com.study.stockmanagementstudycase.service.stock.StockSaleService;
import com.study.stockmanagementstudycase.service.stock.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
@Validated
public class StockController {

    private final StockCreateService stockCreateService;
    private final StockService stockService;
    private final StockEntryService stockEntryService;
    private final StockSaleService stockSaleService;

    @PostMapping
    public ResponseEntity<Void> createStock(
            @RequestBody @Valid final StockCreateRequest stockCreateRequest
    ) {
        stockCreateService.createStock(stockCreateRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{stockId}")
    public ResponseEntity<Void> entryStock(
            @PathVariable("stockId") @UUID final String stockId,
            @RequestBody @Valid final StockEntryRequest stockEntryRequest
    ) {
        stockEntryService.entryStock(stockId, stockEntryRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<StockResponse>> getStocks() {
        final List<Stock> stocks = stockService.getStocks();

        final List<StockResponse> stockResponseList = StockDTOMapper
                .stockResponses(stocks);

        return ResponseEntity.ok(stockResponseList);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockResponse> getStockById(
            @PathVariable("stockId") @UUID final String stockId
    ) {
        final Stock stock = stockService.getStockById(stockId);

        final StockResponse stockResponse = StockDTOMapper
                .toStockResponse(stock);

        return ResponseEntity.ok(stockResponse);
    }

    @PostMapping("{stockId}/sale")
    public ResponseEntity<Void> sellStock(
            @PathVariable("stockId") @UUID final String stockId,
            @RequestBody @Valid final StockSaleRequest stockSaleRequest
    ) {
        stockSaleService.saleStockById(
                stockId,
                stockSaleRequest
        );

        return ResponseEntity.ok().build();
    }
}
