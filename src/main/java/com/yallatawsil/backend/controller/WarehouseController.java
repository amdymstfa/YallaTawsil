package com.yallatawsil.backend.controller;

import com.yallatawsil.backend.dto.request.WarehouseRequestDTO;
import com.yallatawsil.backend.dto.response.WarehouseResponseDTO;
import com.yallatawsil.backend.service.InterfaceEntity.WarehouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Warehouse", description = "Warehouse management APIs")
@Slf4j
public class WarehouseController extends BaseController<WarehouseRequestDTO, WarehouseResponseDTO, Long> {

    public WarehouseController(WarehouseService warehouseService) {
        super(warehouseService);
    }
}