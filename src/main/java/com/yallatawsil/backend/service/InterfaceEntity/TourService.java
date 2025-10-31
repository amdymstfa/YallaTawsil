package com.yallatawsil.backend.service.InterfaceEntity;

import com.yallatawsil.backend.dto.request.TourRequestDTO;
import com.yallatawsil.backend.dto.response.OptimizationComparisonDTO;
import com.yallatawsil.backend.dto.response.TourResponseDTO;

import java.util.List;

public interface TourService {

    TourResponseDTO optimizeTour(TourRequestDTO dto);

    TourResponseDTO findById(Long id);

    List<TourResponseDTO> findAll();

    void delete(Long id);

    OptimizationComparisonDTO compareAlgorithms(TourRequestDTO dto);

    Double getTotalDistance(Long id);
}