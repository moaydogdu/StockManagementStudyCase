package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.dto.response.WareHouseResponse;
import com.study.stockmanagementstudycase.model.mappers.WareHouseDtoMapper;
import com.study.stockmanagementstudycase.service.WareHouseCreateService;
import com.study.stockmanagementstudycase.service.WareHouseService;
import com.study.stockmanagementstudycase.service.WareHouseUpdateService;
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
    private final WareHouseUpdateService wareHouseUpdateService;

    /**
     * Retrieves a list of warehouses.
     *
     * @return ResponseEntity containing a list of WareHouseResponse objects
     */
    @GetMapping()
    public ResponseEntity<List<WareHouseResponse>> getWareHouses() {
        final List<WareHouse> wareHouses = wareHouseService.getWareHouses();
        final List<WareHouseResponse> wareHouseResponseList = WareHouseDtoMapper
                .toGetResponse(wareHouses);
        return ResponseEntity.ok(wareHouseResponseList);
    }

    /**
     * Create a new warehouse.
     *
     * @param request The warehouse data.
     * @return A ResponseEntity with no content.
     */
    @PostMapping
    public ResponseEntity<Void> createWareHouse(
            @RequestBody @Valid final WareHouseCreateRequest request
    ) {
        wareHouseCreateService.createWareHouse(request);

        return ResponseEntity.ok().build();
    }


    /**
     * Update a warehouse by its ID.
     *
     * @param updateRequest The updated warehouse data.
     * @param warehouseId The ID of the warehouse to update.
     * @return A ResponseEntity with no content.
     */
    @PutMapping("/{warehouseId}")
    public ResponseEntity<Void> updateWareHouse(
            @RequestBody @Valid final WareHouseUpdateRequest updateRequest,
            @PathVariable("warehouseId") final String warehouseId
    ) {
        wareHouseUpdateService.updateWareHouse(updateRequest, warehouseId);

       return ResponseEntity.ok().build();
    }
}
