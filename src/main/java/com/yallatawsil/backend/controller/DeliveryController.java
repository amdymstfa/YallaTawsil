package com.yallatawsil.backend.controller;

import com.yallatawsil.backend.dto.request.DeliveryRequestDTO;
import com.yallatawsil.backend.dto.response.DeliveryResponseDTO;
import com.yallatawsil.backend.entity.enums.DeliveryStatus;
import com.yallatawsil.backend.service.InterfaceEntity.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Delivery", description = "Delivery management APIs")
@Slf4j
public class DeliveryController extends BaseController<DeliveryRequestDTO, DeliveryResponseDTO, Long> {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        super(deliveryService);
        this.deliveryService = deliveryService;
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update delivery status")
    public ResponseEntity<DeliveryResponseDTO> updateStatus(@PathVariable Long id, @RequestParam DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.updateStatus(id, status));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get deliveries by status")
    public ResponseEntity<List<DeliveryResponseDTO>> getByStatus(@PathVariable DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.findByStatus(status));
    }

    @GetMapping("/pending")
    @Operation(summary = "Get all pending deliveries")
    public ResponseEntity<List<DeliveryResponseDTO>> getPending() {
        return ResponseEntity.ok(deliveryService.findPendingDeliveries());
    }
}