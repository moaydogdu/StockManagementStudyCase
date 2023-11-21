package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.mappers.WareHouseDTOMapper;
import com.study.stockmanagementstudycase.service.WareHouseCreateService;
import com.study.stockmanagementstudycase.service.WareHouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/warehouses")
@RequiredArgsConstructor
public class WareHouseController {

    private final WareHouseCreateService wareHouseCreateService;
    private final WareHouseService wareHouseService;

    @GetMapping()
    public ResponseEntity<List<WareHouseResponse>> getWareHouses(
    ) {
        final List<WareHouse> wareHouses = wareHouseService
                .getWareHouses();
        final List<WareHouseResponse> wareHouseResponseList = WareHouseDTOMapper
                .toWareHouseResponse(wareHouses);

        return ResponseEntity.ok(wareHouseResponseList);
    }

    @PostMapping
    public ResponseEntity<Void> createWareHouse(
            @RequestBody @Valid final WareHouseCreateRequest request
    ) {
        wareHouseCreateService.createWareHouse(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{wareHouseId}")
    public ResponseEntity<WareHouseResponse> getWareHouseById(
            @PathVariable("wareHouseId") final String wareHouseId
    ){
        final WareHouse wareHouse = wareHouseService
                .getWareHouseById(wareHouseId);
        final WareHouseResponse wareHouseResponse = WareHouseDTOMapper
                .toWareHouseResponse(wareHouse);

        return ResponseEntity.ok(wareHouseResponse);
    }

}
