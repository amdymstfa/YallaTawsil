package com.yallatawsil.backend.service.InterfaceEntityImpl ;

import com.yallatawsil.backend.dto.request.DeliveryRequestDTO;
import com.yallatawsil.backend.dto.response.DeliveryResponseDTO;
import com.yallatawsil.backend.entity.Delivery;
import com.yallatawsil.backend.entity.enums.DeliveryStatus;
import com.yallatawsil.backend.mapper.DeliveryMapper;
import com.yallatawsil.backend.repository.DeliveryRepository;
import com.yallatawsil.backend.service.BaseServiceImpl.BaseServiceImpl;
import com.yallatawsil.backend.service.InterfaceEntity.DeliveryService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DeliveryServiceImp extends BaseServiceImpl<Delivery, DeliveryRequestDTO, DeliveryResponseDTO, Long>
implements DeliveryService
{
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper ;

    public DeliveryServiceImp(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper){
        super(deliveryRepository, "Delivery");
        this.deliveryRepository = deliveryRepository ;
        this.deliveryMapper = deliveryMapper ;
    }


    @Override
    protected Delivery toEntity(DeliveryRequestDTO deliveryRequestDTO) {
        return deliveryMapper.toEntity(deliveryRequestDTO);
    }


    @Override
    protected DeliveryResponseDTO toResponseDTO(Delivery delivery) {
        return deliveryMapper.toResponseDTO(delivery);
    }

    @Override
    protected void updateEntityFromDTO(DeliveryRequestDTO deliveryRequestDTO, Delivery delivery) {

        delivery.setAddress(deliveryRequestDTO.getAddress());
        delivery.setLatitude(deliveryRequestDTO.getLatitude());
        delivery.setLongitude(deliveryRequestDTO.getLongitude());
        delivery.setWeight(deliveryRequestDTO.getWeight());
        delivery.setVolume(deliveryRequestDTO.getVolume());
        delivery.setPreferredTimeSlot(deliveryRequestDTO.getPreferredTimeSlot());
        delivery.setNotes(deliveryRequestDTO.getNotes());
    }

    @Override
    protected Long getEntityId(Delivery delivery) {
        return delivery.getId();
    }

    public DeliveryResponseDTO updateStatus(Long id, DeliveryStatus newStatus){
        log.debug("Set new status for id : {}", id);

        // Find delivery by id
        Delivery delivery = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Delivery not find with id {}", id));

        // Set new status
        delivery.updateStatus(newStatus);
        Delivery updated = deliveryRepository.save(delivery);

        log.info("Delivery status updated to {} for id: {}", newStatus, id);
        return deliveryMapper.toResponseDTO(updated);
    }

    public List<DeliveryResponseDTO> findByStatus(DeliveryStatus status) {
        log.debug("Finding delivery with status : {}", status);

        return deliveryRepository.findByStatus(status).stream()
                .map(deliveryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<DeliveryResponseDTO> findPendingDeliveries() {
        log.debug("Find delivery with status pending");

        return deliveryRepository.findPendingDeliveries().stream()
                .map(deliveryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}