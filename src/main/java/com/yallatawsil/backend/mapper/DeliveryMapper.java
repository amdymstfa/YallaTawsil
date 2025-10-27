package com.yallatawsil.backend.mapper ;

import com.yallatawsil.backend.dto.request.DeliveryRequestDTO;
import com.yallatawsil.backend.dto.response.DeliveryResponseDTO;
import com.yallatawsil.backend.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    // Convert DTO to Entity
    DeliveryResponseDTO toResponseDTO(Delivery entity);
    // Convert Entity to DTO
    Delivery toEntity(DeliveryRequestDTO dto);
}