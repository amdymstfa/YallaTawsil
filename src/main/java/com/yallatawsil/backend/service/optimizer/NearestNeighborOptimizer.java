package com.yallatawsil.backend.service.optimizer;

import com.yallatawsil.backend.entity.Delivery;
import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.entity.Warehouse;
import com.yallatawsil.backend.service.distance.DistanceCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighborOptimizer extends TourOptimizer {

    public NearestNeighborOptimizer(DistanceCalculator distanceCalculator) {
        super(distanceCalculator);
    }

    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries, Vehicle vehicle) {
        validateInput(warehouse, deliveries, vehicle);

        List<Delivery> tour = new ArrayList<>();
        Set<Delivery> visited = new HashSet<>();

        double currentLat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        while (visited.size() < deliveries.size()) {
            Delivery nearest = findNearestDelivery(currentLat, currentLon, deliveries, visited);
            if (nearest == null) break;

            tour.add(nearest);
            visited.add(nearest);
            currentLat = nearest.getLatitude();
            currentLon = nearest.getLongitude();
        }

        return tour;
    }

    private Delivery findNearestDelivery(double lat, double lon, List<Delivery> deliveries, Set<Delivery> visited) {
        Delivery nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Delivery delivery : deliveries) {
            if (visited.contains(delivery)) continue;

            double distance = distanceCalculator.calculateDistance(
                    lat, lon,
                    delivery.getLatitude(), delivery.getLongitude()
            );

            if (distance < minDistance) {
                minDistance = distance;
                nearest = delivery;
            }
        }

        return nearest;
    }
}