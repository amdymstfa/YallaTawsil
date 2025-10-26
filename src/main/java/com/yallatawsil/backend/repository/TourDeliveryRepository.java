package com.yallatawsil.backend.repository;

import com.yallatawsil.backend.entity.Delivery;
import com.yallatawsil.backend.entity.Tour;
import com.yallatawsil.backend.entity.TourDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * TourDelivery Repository
 * Data access layer for TourDelivery junction entity
 */
public interface TourDeliveryRepository extends JpaRepository<TourDelivery, Long> {

    /**
     * Find all tour deliveries for a specific tour, ordered by sequence
     * @param tour the tour
     * @return list of tour deliveries in order
     */
    List<TourDelivery> findByTourOrderBySequenceOrderAsc(Tour tour);

    /**
     * Find all tours that include a specific delivery
     * @param delivery the delivery
     * @return list of tour deliveries
     */
    List<TourDelivery> findByDelivery(Delivery delivery);

    /**
     * Find a specific tour delivery by tour and delivery
     * @param tour the tour
     * @param delivery the delivery
     * @return Optional containing the tour delivery if found
     */
    Optional<TourDelivery> findByTourAndDelivery(Tour tour, Delivery delivery);

    /**
     * Get maximum sequence order for a tour
     * @param tour the tour
     * @return maximum sequence order, or 0 if tour has no deliveries
     */
    @Query("SELECT COALESCE(MAX(td.sequenceOrder), 0) FROM TourDelivery td WHERE td.tour = :tour")
    Integer getMaxSequenceOrder(@Param("tour") Tour tour);

    /**
     * Calculate total distance for a tour
     * @param tour the tour
     * @return total distance in km
     */
    @Query("SELECT COALESCE(SUM(td.distanceFromPrevious), 0.0) FROM TourDelivery td WHERE td.tour = :tour")
    Double getTotalDistanceForTour(@Param("tour") Tour tour);

    /**
     * Count deliveries in a tour
     * @param tour the tour
     * @return number of deliveries
     */
    long countByTour(Tour tour);

    /**
     * Delete all tour deliveries for a specific tour
     * @param tour the tour
     */
    void deleteByTour(Tour tour);
}