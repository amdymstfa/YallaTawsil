package com.yallatawsil.backend.mapper ;

import com.yallatawsil.backend.dto.request.VehicleRequestDTO;
import com.yallatawsil.backend.dto.response.VehicleResponseDTO;
import com.yallatawsil.backend.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    // Convert dto to entity
    VehicleResponseDTO toResponseDTO(Vehicle vehicle);
    // Convert entity to dto
    Vehicle toEntity(VehicleRequestDTO dto);
}