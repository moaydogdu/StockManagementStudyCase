package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.dto.request.stock.StockCreateRequest;
import com.study.stockmanagementstudycase.service.stock.StockCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockCreateService stockCreateService;

    @PostMapping
    public ResponseEntity<Void> createStock(
            @RequestBody @Valid final StockCreateRequest stockCreateRequest
    ) {
        stockCreateService.createStock(stockCreateRequest);

        return ResponseEntity.ok().build();
    }

}
