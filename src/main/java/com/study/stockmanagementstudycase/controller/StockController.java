package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.aggregate.wareHouseStock.WareHouseStockAggregateWithWareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockEntryRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockPagingRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockSaleRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.dto.response.stock.StockResponse;
import com.study.stockmanagementstudycase.model.dto.response.wareHouseStock.WareHouseStockResponse;
import com.study.stockmanagementstudycase.model.mappers.stock.StockDTOMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import com.study.stockmanagementstudycase.service.stock.StockDeleteService;
import com.study.stockmanagementstudycase.service.stock.StockService;
import com.study.stockmanagementstudycase.service.stock.StockEntryService;
import com.study.stockmanagementstudycase.service.stock.StockSaleService;
import com.study.stockmanagementstudycase.service.stock.StockUpdateService;
import com.study.stockmanagementstudycase.service.wareHouseStock.WareHouseStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


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
    private final StockUpdateService stockUpdateService;
    private final StockDeleteService stockDeleteService;

    private final WareHouseStockService wareHouseStockService;

    @PostMapping
    public ResponseEntity<String> createStock(
            @RequestBody @Valid final StockCreateRequest stockCreateRequest
    ) {
        final Stock stock = stockCreateService
                .createStock(stockCreateRequest);

        return ResponseEntity.ok(stock.getId());
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
    public ResponseEntity<CustomPagingResponse<StockResponse>> getStocks(
            @RequestBody @Valid final StockPagingRequest stockPagingRequest
    ) {
        final CustomPage<Stock> stocks = stockService.
                getStocks(stockPagingRequest);

        final CustomPagingResponse<StockResponse> stockResponseList = StockDTOMapper
                .toPagingResponse(stocks);

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

    @PostMapping("/{stockId}/sale")
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

    @GetMapping("/{stockId}/wareHouseStocks")
    public ResponseEntity<List<WareHouseStockResponse>> getWareHouseStocks(
            @PathVariable("stockId") @UUID final String stockId
    ) {
        final Stock stock = stockService.getStockById(stockId);

        final List<WareHouseStockAggregateWithWareHouse> wareHouseStocksByStock = wareHouseStockService
                .getWareHouseStocksByStock(stock);

        final List<WareHouseStockResponse> wareHouseStockResponses = WareHouseStockMapper
                .toWareHouseStockResponse(wareHouseStocksByStock);

        return ResponseEntity.ok(wareHouseStockResponses);
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<Void> updateStock(
            @RequestBody @Valid final StockUpdateRequest updateRequest,
            @PathVariable("stockId") @UUID final String stockId
    ) {
        stockUpdateService.updateStock(
                updateRequest,
                stockId
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> deleteStock(
            @PathVariable("stockId") @UUID final String stockId
    ) {
        stockDeleteService.deleteStock(stockId);

        return ResponseEntity.ok().build();
    }

}
