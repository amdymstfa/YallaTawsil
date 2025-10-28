package com.yallatawsil.backend.service.optimizer;

import com.yallatawsil.backend.entity.Delivery;
import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.entity.Warehouse;
import com.yallatawsil.backend.service.distance.DistanceCalculator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class TourOptimizer {

    protected final DistanceCalculator distanceCalculator;

    /**
     * Calculate optimal tour for given deliveries
     * @param warehouse Starting/ending point
     * @param deliveries List of deliveries to optimize
     * @param vehicle Vehicle constraints
     * @return Ordered list of deliveries
     */
    public abstract List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries, Vehicle vehicle);

    /**
     * Calculate total distance for an ordered list of deliveries
     * @param warehouse Starting/ending point
     * @param orderedDeliveries Deliveries in tour order
     * @return Total distance in km
     */
    public double getTotalDistance(Warehouse warehouse, List<Delivery> orderedDeliveries) {
        if (orderedDeliveries == null || orderedDeliveries.isEmpty()) {
            return 0.0;
        }

        double totalDistance = 0.0;
        double currentLat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        for (Delivery delivery : orderedDeliveries) {
            totalDistance += distanceCalculator.calculateDistance(
                    currentLat, currentLon,
                    delivery.getLatitude(), delivery.getLongitude()
            );
            currentLat = delivery.getLatitude();
            currentLon = delivery.getLongitude();
        }

        // Return to warehouse
        totalDistance += distanceCalculator.calculateDistance(
                currentLat, currentLon,
                warehouse.getLatitude(), warehouse.getLongitude()
        );

        return totalDistance;
    }

    /**
     * Validate deliveries and vehicle capacity
     */
    protected void validateInput(Warehouse warehouse, List<Delivery> deliveries, Vehicle vehicle) {
        if (warehouse == null) {
            throw new IllegalArgumentException("Warehouse cannot be null");
        }
        if (deliveries == null || deliveries.isEmpty()) {
            throw new IllegalArgumentException("Deliveries list cannot be null or empty");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (!vehicle.canAccommodate(deliveries)) {
            throw new IllegalArgumentException("Vehicle cannot accommodate all deliveries");
        }
    }
}