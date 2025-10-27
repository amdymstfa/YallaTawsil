package com.yallatawsil.backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponseDTO {

    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}