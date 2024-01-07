package com.study.stockmanagementstudycase.controller;

import com.study.stockmanagementstudycase.common.model.dto.CustomPage;
import com.study.stockmanagementstudycase.common.model.dto.CustomPagingResponse;
import com.study.stockmanagementstudycase.model.WareHouse;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseCreateRequest;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHousePagingRequest;
import com.study.stockmanagementstudycase.model.dto.request.wareHouse.WareHouseUpdateRequest;
import com.study.stockmanagementstudycase.model.dto.response.wareHouse.WareHouseResponse;
import com.study.stockmanagementstudycase.model.mappers.wareHouse.WareHouseDTOMapper;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseCreateService;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseDeleteService;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseService;
import com.study.stockmanagementstudycase.service.wareHouse.WareHouseUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/v1/warehouses")
@RequiredArgsConstructor
@Validated
public class WareHouseController {

    private final WareHouseCreateService wareHouseCreateService;
    private final WareHouseService wareHouseService;
    private final WareHouseDeleteService wareHouseDeleteService;
    private final WareHouseUpdateService wareHouseUpdateService;


    /**
     * Create a new warehouse.
     *
     * @param request The warehouse data.
     * @return A ResponseEntity with no content.
     */
    @PostMapping
    public ResponseEntity<String> createWareHouse(
            @RequestBody final WareHouseCreateRequest request
    ) {
        final WareHouse wareHouse = wareHouseCreateService
                .createWareHouse(request);

        return ResponseEntity.ok(wareHouse.getId());
    }

    /**
     * Retrieves a list of warehouses.
     *
     * @return ResponseEntity containing a list of WareHouseResponse objects
     */
    @GetMapping()
    public ResponseEntity<CustomPagingResponse<WareHouseResponse>> getWareHouses(
            @RequestBody @Valid final WareHousePagingRequest wareHousePagingRequest
    ) {
        final CustomPage<WareHouse> wareHouses = wareHouseService
                .getWareHouses(wareHousePagingRequest);

        final CustomPagingResponse<WareHouseResponse> wareHouseResponseList = WareHouseDTOMapper
                .toPagingResponse(wareHouses);

        return ResponseEntity.ok(wareHouseResponseList);
    }

    @GetMapping("/{wareHouseId}")
    public ResponseEntity<WareHouseResponse> getWareHouseById(
            @PathVariable("wareHouseId") @UUID String wareHouseId
    ) {
        final WareHouse wareHouse = wareHouseService
                .getWareHouseById(wareHouseId);
        final WareHouseResponse wareHouseResponse = WareHouseDTOMapper
                .toWareHouseResponse(wareHouse);

        return ResponseEntity.ok(wareHouseResponse);
    }

    @GetMapping("/deleted")
    public ResponseEntity<CustomPagingResponse<WareHouseResponse>> getDeletedWareHouses(
            @RequestBody @Valid final WareHousePagingRequest wareHousePagingRequest
    ) {
        final CustomPage<WareHouse> deletedWareHouses = wareHouseService
                .getDeletedWareHouses(wareHousePagingRequest);

        final CustomPagingResponse<WareHouseResponse> response = WareHouseDTOMapper
                .toPagingResponse(deletedWareHouses);

        return ResponseEntity.ok(response);
    }

    /**
     * Update a warehouse by its ID.
     *
     * @param updateRequest The updated warehouse data.
     * @param wareHouseId   The ID of the warehouse to update.
     * @return A ResponseEntity with no content.
     */
    @PutMapping("/{wareHouseId}")
    public ResponseEntity<Void> updateWareHouse(
            @RequestBody @Valid final WareHouseUpdateRequest updateRequest,
            @PathVariable("wareHouseId") @UUID final String wareHouseId
    ) {
        wareHouseUpdateService.updateWareHouse(
                updateRequest,
                wareHouseId
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{wareHouseId}")
    public ResponseEntity<Void> deleteWareHouse(
            @PathVariable("wareHouseId") @UUID final String wareHouseId
    ) {
        wareHouseDeleteService.deleteWareHouse(wareHouseId);

        return ResponseEntity.ok().build();
    }

}
