package com.yallatawsil.backend.mapper ;

import com.yallatawsil.backend.dto.request.TourRequestDTO;
import com.yallatawsil.backend.dto.response.TourResponseDTO;
import com.yallatawsil.backend.entity.Tour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourMapper {
    // Convert dto to entity
    TourResponseDTO toResponseDTO(Tour tour);
    // Convert entity to dto
    Mapper toEntity(TourRequestDTO dto);
}