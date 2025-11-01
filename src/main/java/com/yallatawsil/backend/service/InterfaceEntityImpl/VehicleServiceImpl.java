package com.yallatawsil.backend.service.InterfaceEntityImpl ;

import com.yallatawsil.backend.dto.request.VehicleRequestDTO;
import com.yallatawsil.backend.dto.response.VehicleResponseDTO;
import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.mapper.VehicleMapper;
import com.yallatawsil.backend.repository.VehicleRepository;
import com.yallatawsil.backend.service.BaseServiceImpl.BaseServiceImpl;
import com.yallatawsil.backend.service.InterfaceEntity.VehicleService;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Setter
@Getter
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, VehicleRequestDTO, VehicleResponseDTO, Long>
        implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper){
        super(vehicleRepository, "Vehicle");
        this.vehicleRepository = vehicleRepository ;
        this.vehicleMapper = vehicleMapper ;
    }

    @Override
    protected Vehicle toEntity(VehicleRequestDTO dto) {
        return null;
    }

    @Override
    public VehicleResponseDTO toResponseDTO(Entity entity) {
        return null;
    }

    @Override
    protected void updateEntityFromDTO(VehicleRequestDTO dto, Vehicle entity) {

    }

    @Override
    protected VehicleResponseDTO toResponseDTO(Vehicle vehicle) {
        return null;
    }

    @Override
    protected Long getEntityId(Vehicle vehicle) {
        return 0L;
    }

    @Override
    public List<VehicleResponseDTO> findAvailableVehicles(LocalDate date) {
        return List.of();
    }
}

