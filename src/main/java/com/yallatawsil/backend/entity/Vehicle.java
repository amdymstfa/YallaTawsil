package com.yallatawsil.backend.entity ;

/**
 * Vehicles entity
 * Represent a delivery vehicle with specific capacity constraints
 */

import com.yallatawsil.backend.entity.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle is a required type")
    @Column(nullable = false)
    private VehicleType type ;

    @NotNull(message = "Max Weight is required")
    @Column(nullable = false)
    private Double maxWeight ;

    @NotNull(message = "Max Volume is a required")
    @Column(nullable = false)
    private Double maxVolume ;

    @NotNull(message = "Max Deliveries is required")
    @Column(nullable = false)
    private Double maxDeliveries ;

    @NotNull(message = "licensePlate is required")
    @Column(nullable = false, unique = true, length = 20)
    private String licensePlate ;

    @Column(updatable = false)
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;

    /**
     * Lifecycle callback executed before entity is persisted
     */
    @PrePersist
    protected void onCreated(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.type != null) {
            if (this.maxWeight == null) this.maxWeight = this.type.getDefaultMaxWeight();
            if (this.maxVolume == null) this.maxVolume = this.type.getDefaultMaxVolume() ;
            if (this.maxDeliveries == null) this.maxDeliveries = Double.valueOf(this.type.getDefaultMaxDeliveries());
        }
    }

    /**
     * Automatically update timestamp on modification
     */
    @PreUpdate
    protected void onUpdate(){this.updatedAt = LocalDateTime.now();}

    /**
     * Check if this vehicle can accommodate a list of deliveries
     * @param deliveries list of deliveries to check
     * @return true if vehicle can handle all deliveries, false otherwise
     */
    public boolean canAccommodate(List<Delivery> deliveries){
        if (deliveries == null || deliveries.isEmpty()){
            return true;
        }

        if (deliveries.size() > this.maxDeliveries){return false ;}

        double totalWeight = deliveries.stream()
                .mapToDouble(Delivery::getWeight);
                .sum();

        double totalVolume = deliveries.stream()
                .mapToDouble(Delivery::getVolume)
                .sum();

        return totalWeight <= this.maxWeight && totalVolume <= this.maxVolume ;
    }
}
