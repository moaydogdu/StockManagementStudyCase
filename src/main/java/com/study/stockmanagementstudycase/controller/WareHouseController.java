package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.service.WareHouseCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/warehouses")
@RequiredArgsConstructor
public class WareHouseController {

    private final WareHouseCreateService wareHouseCreateService;

    @PostMapping
    public ResponseEntity createWareHouse(
            @RequestBody @Valid WareHouseCreateRequest request
    ) {
        WareHouseEntity createdWareHouseEntity = wareHouseCreateService
                .createWareHouse(request);

        return ResponseEntity.ok().build();
    }
}
