package com.yallatawsil.backend.controller;

import com.yallatawsil.backend.dto.request.VehicleRequestDTO;
import com.yallatawsil.backend.dto.response.VehicleResponseDTO;
import com.yallatawsil.backend.service.InterfaceEntity.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Vehicle", description = "Vehicle management APIs")
@Slf4j
public class VehicleController extends BaseController<VehicleRequestDTO, VehicleResponseDTO, Long> {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        super(vehicleService);
        this.vehicleService = vehicleService;
    }

    @GetMapping("/available")
    @Operation(summary = "Get available vehicles for a specific date")
    public ResponseEntity<List<VehicleResponseDTO>> getAvailableVehicles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(vehicleService.findAvailableVehicles(date));
    }
}