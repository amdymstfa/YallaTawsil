package com.yallatawsil.backend.dto.response;

import com.yallatawsil.backend.entity.enums.VehicleType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponseDTO {

    private Long id;
    private VehicleType type;
    private Double maxWeight;
    private Double maxVolume;
    private Integer maxDeliveries;
    private String licensePlate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}