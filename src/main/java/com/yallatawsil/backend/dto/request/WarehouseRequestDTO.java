package com.yallatawsil.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRequestDTO {

    @NotBlank(message = "Name of warehouse is required")
    private String name;

    @NotBlank(message = "Address of warehouse is required")
    private String address;

    @NotNull(message = "Longitude is required")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    private Double longitude;

    @NotNull(message = "Latitude is required")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    private Double latitude;

    private LocalTime openingTime;
    private LocalTime closingTime;
}
