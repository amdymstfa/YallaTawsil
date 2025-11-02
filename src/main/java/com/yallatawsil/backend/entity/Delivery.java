package com.yallatawsil.backend.entity;

import com.yallatawsil.backend.entity.enums.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Delivery Entity
 * Represents a single delivery with GPS coordinates and constraints
 */
@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Delivery address is required")
    @Column(nullable = false, length = 500)
    private String address;

    @NotNull(message = "Latitude is required")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    @Column(nullable = false)
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    @Column(nullable = false)
    private Double longitude;

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    @Column(nullable = false)
    private Double weight;

    @NotNull(message = "Volume is required")
    @DecimalMin(value = "0.01", message = "Volume must be greater than 0")
    @Column(nullable = false)
    private Double volume;

    // REMOVE @NotNull - will be set in @PrePersist
    @Column
    private LocalTime preferredStartTime;

    // REMOVE @NotNull - will be set in @PrePersist
    @Column
    private LocalTime preferredEndTime;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    @Column(nullable = false, length = 20)
    private DeliveryStatus status;

    @Column(length = 1000)
    private String notes;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Automatically set creation timestamp and default status
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        // Set default status if not provided
        if (this.status == null) {
            this.status = DeliveryStatus.PENDING;
        }

        // Set default time window if not provided (full business day)
        if (this.preferredStartTime == null) {
            this.preferredStartTime = LocalTime.of(8, 0);
        }
        if (this.preferredEndTime == null) {
            this.preferredEndTime = LocalTime.of(18, 0);
        }

        // Validate on creation
        validateConstraints();
    }

    /**
     * Automatically update timestamp on modification and validate
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();

        // Validate on update
        validateConstraints();
    }

    /**
     * Validate on load
     */
    @PostLoad
    private void onLoad() {
        validateConstraints();
    }

    /**
     * Update delivery status with validation
     * @param newStatus the new status to set
     * @throws IllegalStateException if transition is not allowed
     */
    public void updateStatus(DeliveryStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    String.format("Cannot transition from %s to %s", this.status, newStatus)
            );
        }
        this.status = newStatus;
    }

    /**
     * Validate weight, volume and preferred time slot
     */
    private void validateConstraints() {
        if (this.weight != null && this.weight <= 0) {
            throw new IllegalStateException("Weight must be greater than 0");
        }
        if (this.volume != null && this.volume <= 0) {
            throw new IllegalStateException("Volume must be greater than 0");
        }

        if (preferredStartTime != null && preferredEndTime != null) {
            if (!preferredStartTime.isBefore(preferredEndTime)) {
                throw new IllegalStateException("Preferred start time must be before preferred end time");
            }
        }
    }

    /**
     * Set preferred time slot based on enum value
     * @param timeSlot MORNING, AFTERNOON, or EVENING
     */
    public void setPreferredTimeSlot(String timeSlot) {
        if (timeSlot == null) {
            // Default to full business day
            this.preferredStartTime = LocalTime.of(8, 0);
            this.preferredEndTime = LocalTime.of(18, 0);
            return;
        }

        switch (timeSlot.toUpperCase()) {
            case "MORNING":
                this.preferredStartTime = LocalTime.of(8, 0);
                this.preferredEndTime = LocalTime.of(12, 0);
                break;
            case "AFTERNOON":
                this.preferredStartTime = LocalTime.of(12, 0);
                this.preferredEndTime = LocalTime.of(17, 0);
                break;
            case "EVENING":
                this.preferredStartTime = LocalTime.of(17, 0);
                this.preferredEndTime = LocalTime.of(21, 0);
                break;
            default:
                // Full business day for unknown values
                this.preferredStartTime = LocalTime.of(8, 0);
                this.preferredEndTime = LocalTime.of(18, 0);
        }
    }
}