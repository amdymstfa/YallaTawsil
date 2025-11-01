package com.yallatawsil.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourRequestDTO {

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Warehouse ID is required")
    private Long warehouseId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotEmpty(message = "At least one delivery is required")
    private List<Long> deliveryIds;

    @NotNull(message = "Optimization algorithm is required")
    private String optimizationAlgorithm;
}