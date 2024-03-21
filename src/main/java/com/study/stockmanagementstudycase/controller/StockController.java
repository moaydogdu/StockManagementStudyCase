package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.common.model.dto.CustomResponse;
import com.study.stockmanagementstudycase.model.Stock;
import com.study.stockmanagementstudycase.model.aggregate.wareHouseStock.WareHouseStockAggregateWithWareHouse;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockEntryRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockPagingRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockSaleRequest;
import com.study.stockmanagementstudycase.model.dto.request.stock.StockUpdateRequest;
import com.study.stockmanagementstudycase.model.dto.request.wareHouseStock.WareHouseStockPagingRequest;
import com.study.stockmanagementstudycase.model.dto.response.stock.StockResponse;
import com.study.stockmanagementstudycase.model.dto.response.stock.StockResponseWithStatus;
import com.study.stockmanagementstudycase.model.dto.response.wareHouseStock.WareHouseStockResponse;
import com.study.stockmanagementstudycase.model.mappers.stock.StockDTOMapper;
import com.study.stockmanagementstudycase.model.mappers.wareHouseStock.WareHouseStockMapper;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import com.study.stockmanagementstudycase.service.stock.StockDeleteService;
import com.study.stockmanagementstudycase.service.stock.StockEntryService;
import com.study.stockmanagementstudycase.service.stock.StockSaleService;
import com.study.stockmanagementstudycase.service.stock.StockService;
import com.study.stockmanagementstudycase.service.stock.StockUpdateService;
import com.study.stockmanagementstudycase.service.warehouse_stock.WareHouseStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CustomResponse<String> createStock(
            @RequestBody @Valid final StockCreateRequest stockCreateRequest
    ) {
        final Stock stock = stockCreateService
                .createStock(stockCreateRequest);

        return CustomResponse.created(stock.getId());
    }

    @PostMapping("/{stockId}")
    public CustomResponse<Void> entryStock(
            @PathVariable("stockId") @UUID final String stockId,
            @RequestBody @Valid final StockEntryRequest stockEntryRequest
    ) {
        stockEntryService.entryStock(stockId, stockEntryRequest);

        return CustomResponse.SUCCESS;
    }

    @GetMapping
    public CustomResponse<CustomPagingResponse<StockResponse>> getStocks(
            @RequestBody @Valid final StockPagingRequest stockPagingRequest
    ) {
        final CustomPage<Stock> stocks = stockService
                .getStocks(stockPagingRequest);

        final CustomPagingResponse<StockResponse> stockResponseList = StockDTOMapper
                .toPagingResponseWithStockResponse(stocks);

        return CustomResponse.ok(stockResponseList);
    }

    @GetMapping("/{stockId}")
    public CustomResponse<StockResponse> getStockById(
            @PathVariable("stockId") @UUID final String stockId
    ) {
        final Stock stock = stockService.getStockById(stockId);

        final StockResponse stockResponse = StockDTOMapper
                .toStockResponse(stock);

        return CustomResponse.ok(stockResponse);
    }

    @GetMapping("/deleted")
    public CustomResponse<CustomPagingResponse<StockResponse>> getDeletedStocks(
            @RequestBody @Valid final StockPagingRequest stockPagingRequest
    ) {
        final CustomPage<Stock> deletedStocks = stockService
                .getDeletedStocks(stockPagingRequest);

        final CustomPagingResponse<StockResponse> response = StockDTOMapper
                .toPagingResponseWithStockResponse(deletedStocks);

        return CustomResponse.ok(response);
    }

    @GetMapping("/getAll")
    public CustomResponse<CustomPagingResponse<StockResponseWithStatus>> getAllStocks(
            @RequestBody @Valid final StockPagingRequest stockPagingRequest
    ) {
        final CustomPage<Stock> allStocks = stockService
                .getAllStocks(stockPagingRequest);

        final CustomPagingResponse<StockResponseWithStatus> response = StockDTOMapper
                .toPagingResponseWithStockResponseWithStatus(allStocks);

        return CustomResponse.ok(response);
    }

    @PostMapping("/{stockId}/sale")
    public CustomResponse<Void> sellStock(
            @PathVariable("stockId") @UUID final String stockId,
            @RequestBody @Valid final StockSaleRequest stockSaleRequest
    ) {
        stockSaleService.saleStockById(
                stockId,
                stockSaleRequest
        );

        return CustomResponse.SUCCESS;
    }

    @GetMapping("/{stockId}/wareHouseStocks")
    public CustomResponse<CustomPagingResponse<WareHouseStockResponse>> getWareHouseStocks(
            @PathVariable("stockId") @UUID final String stockId,
            @RequestBody @Valid final WareHouseStockPagingRequest wareHouseStockPagingRequest
    ) {
        final Stock stock = stockService.getStockById(stockId);

        final CustomPage<WareHouseStockAggregateWithWareHouse> wareHouseStocksByStock = wareHouseStockService
                .getWareHouseStocksByStock(
                        stock,
                        wareHouseStockPagingRequest
                );

        final CustomPagingResponse<WareHouseStockResponse> wareHouseStockResponses = WareHouseStockMapper
                .toPagingResponse(wareHouseStocksByStock);

        return CustomResponse.ok(wareHouseStockResponses);
    }

    @PutMapping("/{stockId}")
    public CustomResponse<Void> updateStock(
            @RequestBody @Valid final StockUpdateRequest updateRequest,
            @PathVariable("stockId") @UUID final String stockId
    ) {
        stockUpdateService.updateStock(
                updateRequest,
                stockId
        );

        return CustomResponse.SUCCESS;
    }

    @DeleteMapping("/{stockId}")
    public CustomResponse<Void> deleteStock(
            @PathVariable("stockId") @UUID final String stockId
    ) {
        stockDeleteService.deleteStock(stockId);

        return CustomResponse.SUCCESS;
    }

}
