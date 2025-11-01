package com.yallatawsil.backend.service.InterfaceEntity ;

import com.yallatawsil.backend.dto.request.VehicleRequestDTO;
import com.yallatawsil.backend.dto.response.VehicleResponseDTO;
import com.yallatawsil.backend.service.BaseService.BaseService;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService extends BaseService<VehicleRequestDTO, VehicleResponseDTO, Long> {

    VehicleResponseDTO toResponseDTO(Entity entity);

    List<VehicleResponseDTO> findAvailableVehicles(LocalDate date);
}