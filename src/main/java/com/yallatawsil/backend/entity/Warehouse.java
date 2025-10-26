package com.yallatawsil.backend.entity ;

/**
 * Wherehouse entity
 * Represent the starting and ending point of all deliveries tours
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Warehouse {

    private static final LocalTime DEFAULT_OPENING_TIME = LocalTime.of(6,0);
    private static final LocalTime DEFAULT_CLOSING_TIME = LocalTime.of(22,0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotNull(message = "Warehouse name required")
    @Column
    private String name ;

    @NotNull(message = "Address is required")
    @Column(nullable = false, length = 500)
    private String address ;

    @NotNull(message = "Latitude is required")
    @Min(value = -90, message = "Latitude have to be between -90 and 90")
    @Max(value = 90, message = "Latitude have to be between -90 and 90")
    @Column(nullable = false)
    private Double latitude ;

    @NotNull(message = "Longitude is required")
    @Min(value = -180, message = "Longitude have to be between -180 and 180")
    @Max(value = 180, message = "Longitude have to be between -180 and 180")
    @Column(nullable = false)
    private Double longitude ;

    @NotNull(message = "openingTime is required")
    @Column(nullable = false)
    private LocalTime openingTime ;

    @NotNull(message = "closingTime is required")
    @Column(nullable = false)
    private LocalTime closingTime ;

    @Column(updatable = false)
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;

    /**
     * Automatically set timestamp and opening/close time
     */
    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.openingTime == null) this.openingTime = DEFAULT_OPENING_TIME ;
        if (this.closingTime == null) this.closingTime = DEFAULT_OPENING_TIME ;
    }

    /**
     * Automatically update timestamp on modification
     */
    @PreUpdate
    public void onUpdate(){this.updatedAt = LocalDateTime.now();}

    /**
     * Check if the warehouse is open at the specific time
     * @param time the time to check
     * @return true if it's open , false otherwise
     */
    public boolean isOpen(LocalTime time){
        return !time.isBefore(this.openingTime) && !time.isAfter(this.closingTime);
    }

    /**
     * Validate if the opening time is before the closing
     */
    @PostLoad
    @PrePersist
    @PreUpdate
    public void validateOpeningHours(){
        if (this.openingTime != null && this.closingTime != null){
            if (!this.openingTime.isBefore(this.closingTime)){
                throw new IllegalMonitorStateException("Opening time must be before closing time");
            }
        }
    }
}