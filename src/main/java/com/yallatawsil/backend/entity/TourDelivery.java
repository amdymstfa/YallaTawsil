package com.yallatawsil.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

/**
 * TourDelivery Entity
 * Junction table between Tour and Delivery with sequence information.
 * Maintains the order of deliveries in a tour.
 */
@Entity
@Table(name = "tour_deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    @NotNull(message = "Tour is required")
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id", nullable = false)
    @NotNull(message = "Delivery is required")
    private Delivery delivery;

    @NotNull(message = "Sequence order is required")
    @Column(nullable = false)
    private Integer sequenceOrder;

    @Column(nullable = true)
    private Double distanceFromPrevious;

    @Column(nullable = true)
    private LocalDateTime estimatedArrival;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Lifecycle callback: automatically set creation timestamp
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        validateSequenceOrder();
    }

    /**
     * Lifecycle callbacks: validate sequence order on load and before persist/update
     */
    @PostLoad
    @PreUpdate
    private void onValidate() {
        validateSequenceOrder();
    }

    /**
     * Ensure sequenceOrder is positive
     */
    private void validateSequenceOrder() {
        if (this.sequenceOrder == null || this.sequenceOrder < 1) {
            throw new IllegalStateException("Sequence order must be >= 1");
        }
    }
}
