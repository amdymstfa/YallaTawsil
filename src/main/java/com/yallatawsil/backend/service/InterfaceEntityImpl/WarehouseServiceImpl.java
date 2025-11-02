package com.yallatawsil.backend.service.InterfaceEntityImpl;

import com.yallatawsil.backend.dto.request.WarehouseRequestDTO;
import com.yallatawsil.backend.dto.response.WarehouseResponseDTO;
import com.yallatawsil.backend.entity.Warehouse;
import com.yallatawsil.backend.mapper.WarehouseMapper;
import com.yallatawsil.backend.repository.WarehouseRepository;
import com.yallatawsil.backend.service.BaseServiceImpl.BaseServiceImpl;
import com.yallatawsil.backend.service.InterfaceEntity.WarehouseService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
public class WarehouseServiceImpl extends BaseServiceImpl<Warehouse, WarehouseRequestDTO, WarehouseResponseDTO, Long>
        implements WarehouseService {

    private final WarehouseMapper warehouseMapper;
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        super(warehouseRepository, "Warehouse");
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

//    @Override
//    protected Warehouse toEntity(WarehouseRequestDTO dto) {
//        if (warehouseRepository.existsByName(dto.getName())) {
//            throw new IllegalArgumentException("Warehouse with name " + dto.getName() + " already exists");
//        }
//        return warehouseMapper.toEntity(dto);
//    }
@Override
protected Warehouse toEntity(WarehouseRequestDTO dto) {
    try {
        if (warehouseRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Warehouse with name " + dto.getName() + " already exists");
        }

        Warehouse warehouse = warehouseMapper.toEntity(dto);

        if (warehouse.getOpeningTime() == null) {
            warehouse.setOpeningTime(LocalTime.of(6, 0));
        }
        if (warehouse.getClosingTime() == null) {
            warehouse.setClosingTime(LocalTime.of(22, 0));
        }

        return warehouse;
    } catch (Exception e) {
        log.error("Error while converting Warehouse DTO to Entity: {}", e.getMessage(), e);
        throw e;
    }
}




    @Override
    protected WarehouseResponseDTO toResponseDTO(Warehouse entity) {
        return warehouseMapper.toResponseDTO(entity);
    }

    @Override
    protected void updateEntityFromDTO(WarehouseRequestDTO dto, Warehouse entity) {
        if (!entity.getName().equals(dto.getName())
                && warehouseRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Warehouse with name " + dto.getName() + " already exists");
        }

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        if (dto.getOpeningTime() != null) entity.setOpeningTime(dto.getOpeningTime());
        if (dto.getClosingTime() != null) entity.setClosingTime(dto.getClosingTime());
    }

    @Override
    protected Long getEntityId(Warehouse entity) {
        return entity.getId();
    }
}