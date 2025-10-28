package com.yallatawsil.backend.service.optimizer ;

import com.yallatawsil.backend.entity.Delivery;
import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.entity.Warehouse;

import java.util.List;

public interface TourOptimizer {

    /**
     * Calculate the optimize tour for a given delivery
     * @param warehouse Starting and ending point
     * @param deliveries List of deliveries to optimize
     * @param vehicle vehicle Vehicle constraints
     * @return Ordered list of deliveries
     */
    List<Delivery> calculateOptimizedTour(Warehouse warehouse, List<Delivery> deliveries, Vehicle vehicle);

    /**
     * Calculate total distance for an ordered list of deliveries
     * @param warehouse Starting/ending point
     * @param orderedDeliveries Deliveries in tour order
     * @return Total distance in km
     */

    Double calculateTotalDistance(Warehouse warehouse ,List<Delivery> orderedDeliveries);
}