package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.entities.WareHouseEntity;
import com.study.stockmanagementstudycase.service.WareHouseCreateService;
import com.study.stockmanagementstudycase.service.WareHouseGetAllService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/warehouses")
@RequiredArgsConstructor
public class WareHouseController {

    private final WareHouseCreateService wareHouseCreateService;
    private final WareHouseGetAllService wareHouseGetAllService;

    @GetMapping()
    public ResponseEntity<List<WareHouseResponse>> getAllWareHouses() {
        return ResponseEntity.ok(wareHouseGetAllService.getAllWareHouses());
    }

    @PostMapping
    public ResponseEntity createWareHouse(
            @RequestBody @Valid WareHouseCreateRequest request
    ) {
        WareHouseEntity createdWareHouseEntity = wareHouseCreateService
                .createWareHouse(request);

        return ResponseEntity.ok().build();
    }
}
