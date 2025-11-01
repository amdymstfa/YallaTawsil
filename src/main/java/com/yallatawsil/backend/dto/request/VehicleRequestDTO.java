package com.yallatawsil.backend.dto.request ;

/**
 * DTO for creating or updating a Vehicle entity.
 * Contains validation rules for input data.
 */


import com.yallatawsil.backend.entity.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VehicleRequestDTO {

    @NotNull(message = "Type of vehicle required")
    private VehicleType type ;

    @NotBlank(message = "License plate is required")
    private String licensePlate ;

    @Positive(message = "Weight must be greater than 0")
    private Double maxWeight ;

    @Positive(message = "Deliveries must be greater than 0")
    private Double maxDeliveries ;

    @Positive(message = "Volume must be greater than 0")
    private  Double maxVolume ;
}