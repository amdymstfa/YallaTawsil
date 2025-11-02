package com.yallatawsil.backend.mapper;

import com.yallatawsil.backend.dto.request.WarehouseRequestDTO;
import com.yallatawsil.backend.dto.response.WarehouseResponseDTO;
import com.yallatawsil.backend.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    // Convert dto to entity
    WarehouseResponseDTO toResponseDTO(Warehouse warehouse);
    // Convert entity to dto
    Warehouse toEntity(WarehouseRequestDTO dto);
}