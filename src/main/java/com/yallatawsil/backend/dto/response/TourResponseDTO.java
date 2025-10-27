package com.yallatawsil.backend.dto.response;

import com.yallatawsil.backend.entity.enums.TourStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourResponseDTO {

    private Long id;
    private VehicleResponseDTO vehicle;
    private WarehouseResponseDTO warehouse;
    private LocalDate date;
    private List<DeliveryResponseDTO> orderedDeliveries;
    private Double totalDistance;
    private String optimizationAlgorithm;
    private Integer estimatedDurationMinutes;
    private TourStatus status;
    private Integer deliveryCount;
    private Double totalWeight;
    private Double totalVolume;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}