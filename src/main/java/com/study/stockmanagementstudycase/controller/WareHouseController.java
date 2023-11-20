package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.mappers.WareHouseDtoMapper;
import com.study.stockmanagementstudycase.service.WareHouseCreateService;
import com.study.stockmanagementstudycase.service.WareHouseDeleteService;
import com.study.stockmanagementstudycase.service.WareHouseService;
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
    private final WareHouseService wareHouseService;
    private final WareHouseDeleteService wareHouseDeleteService;

    @GetMapping()
    public ResponseEntity<List<WareHouseResponse>> getWareHouses() {
        final List<WareHouse> wareHouses = wareHouseService.getWareHouses();
        final List<WareHouseResponse> wareHouseResponseList = WareHouseDtoMapper
                .toGetResponse(wareHouses);
        return ResponseEntity.ok(wareHouseResponseList);
    }

    @PostMapping
    public ResponseEntity<Void> createWareHouse(
            @RequestBody @Valid final WareHouseCreateRequest request
    ) {
        wareHouseCreateService.createWareHouse(request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{wareHouseId}")
    public ResponseEntity<Void> deleteWareHouse(
            @PathVariable("wareHouseId") final String wareHouseId
    ) {
        wareHouseDeleteService.deleteWareHouse(wareHouseId);

        return ResponseEntity.ok().build();
    }
}
