package com.yallatawsil.backend.service.InterfaceEntity ;

import com.yallatawsil.backend.dto.request.DeliveryRequestDTO;
import com.yallatawsil.backend.dto.response.DeliveryResponseDTO;
import com.yallatawsil.backend.entity.enums.DeliveryStatus;
import com.yallatawsil.backend.service.BaseService.BaseService;

import java.util.List;

public interface DeliveryService extends BaseService<DeliveryRequestDTO, DeliveryResponseDTO, Long>
{
    DeliveryResponseDTO updateStatus(Long id, DeliveryStatus newStatus);

    List<DeliveryResponseDTO> findByStatus(DeliveryStatus status);

    List<DeliveryResponseDTO> findPendingDeliveries();
}