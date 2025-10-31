package com.yallatawsil.backend.controller;

import com.yallatawsil.backend.dto.request.VehicleRequestDTO;
import com.yallatawsil.backend.dto.response.VehicleResponseDTO;
import com.yallatawsil.backend.service.InterfaceEntity.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Vehicle", description = "Vehicle management APIs")
@AllArgsConstructor

public class VehicleController {

    private final VehicleService vehicleService ;

    /**
     * Create a new vehicle
     * @param dto of VehicleRequestDTO
     * @return ResponseEntity of VehicleResponseDTO
     */

    @PostMapping
    @Operation(summary = "Create a new Vehicle")
    public ResponseEntity<VehicleResponseDTO> createVehicle(@Valid @RequestBody VehicleRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(dto));
    }

    /**
     * Find a vehicle by his id
     * @param id of type Long
     * @return response ok of ResponseEntity
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a vehicle by ID")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.findById(id));
    }


    @GetMapping
    @Operation(summary = "Get all vehicle")
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a vehicle by ID")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleRequestDTO dto){
        return ResponseEntity.ok(vehicleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    @Operation(summary = "Get available vehicle for a specific date")
    public ResponseEntity<List<VehicleResponseDTO>> getAvailableVehicles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        return ResponseEntity.ok(vehicleService.findAvailableVehicles(date));
    }
}