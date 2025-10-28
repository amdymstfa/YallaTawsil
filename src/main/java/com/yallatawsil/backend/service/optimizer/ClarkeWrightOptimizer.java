package com.yallatawsil.backend.service.optimizer;

import com.yallatawsil.backend.entity.Delivery;
import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.entity.Warehouse;
import com.yallatawsil.backend.service.distance.DistanceCalculator;
import com.yallatawsil.backend.service.optimizer.model.Saving;

import java.util.*;

public class ClarkeWrightOptimizer extends TourOptimizer {

    public ClarkeWrightOptimizer(DistanceCalculator distanceCalculator) {
        super(distanceCalculator);
    }

    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries, Vehicle vehicle) {
        validateInput(warehouse, deliveries, vehicle);

        if (deliveries.size() == 1) {
            return new ArrayList<>(deliveries);
        }

        List<Saving> savings = calculateSavings(warehouse, deliveries);
        Collections.sort(savings);

        return mergeRoutes(savings, deliveries, vehicle);
    }

    private List<Saving> calculateSavings(Warehouse warehouse, List<Delivery> deliveries) {
        List<Saving> savings = new ArrayList<>();

        for (int i = 0; i < deliveries.size(); i++) {
            for (int j = i + 1; j < deliveries.size(); j++) {
                Delivery d1 = deliveries.get(i);
                Delivery d2 = deliveries.get(j);

                double distWD1 = distanceCalculator.calculateDistance(
                        warehouse.getLatitude(), warehouse.getLongitude(),
                        d1.getLatitude(), d1.getLongitude()
                );

                double distWD2 = distanceCalculator.calculateDistance(
                        warehouse.getLatitude(), warehouse.getLongitude(),
                        d2.getLatitude(), d2.getLongitude()
                );

                double distD1D2 = distanceCalculator.calculateDistance(
                        d1.getLatitude(), d1.getLongitude(),
                        d2.getLatitude(), d2.getLongitude()
                );

                double savingValue = distWD1 + distWD2 - distD1D2;
                savings.add(new Saving(d1, d2, savingValue));
            }
        }

        return savings;
    }

    private List<Delivery> mergeRoutes(List<Saving> savings, List<Delivery> deliveries, Vehicle vehicle) {
        Map<Delivery, List<Delivery>> routes = new HashMap<>();

        for (Delivery delivery : deliveries) {
            List<Delivery> route = new ArrayList<>();
            route.add(delivery);
            routes.put(delivery, route);
        }

        Set<Delivery> routeStarts = new HashSet<>(deliveries);
        Set<Delivery> routeEnds = new HashSet<>(deliveries);

        for (Saving saving : savings) {
            Delivery d1 = saving.getDelivery1();
            Delivery d2 = saving.getDelivery2();

            if (routeEnds.contains(d1) && routeStarts.contains(d2)) {
                List<Delivery> route1 = routes.get(d1);
                List<Delivery> route2 = routes.get(d2);

                List<Delivery> merged = new ArrayList<>(route1);
                merged.addAll(route2);

                if (vehicle.canAccommodate(merged)) {
                    route1.addAll(route2);

                    Delivery newEnd = route2.get(route2.size() - 1);
                    routes.put(newEnd, route1);

                    routeEnds.remove(d1);
                    routeStarts.remove(d2);
                    routeEnds.add(newEnd);
                }
            }
            else if (routeEnds.contains(d2) && routeStarts.contains(d1)) {
                List<Delivery> route1 = routes.get(d2);
                List<Delivery> route2 = routes.get(d1);

                List<Delivery> merged = new ArrayList<>(route1);
                merged.addAll(route2);

                if (vehicle.canAccommodate(merged)) {
                    route1.addAll(route2);

                    Delivery newEnd = route2.get(route2.size() - 1);
                    routes.put(newEnd, route1);

                    routeEnds.remove(d2);
                    routeStarts.remove(d1);
                    routeEnds.add(newEnd);
                }
            }
        }

        return routes.values().stream()
                .max(Comparator.comparingInt(List::size))
                .orElse(new ArrayList<>(deliveries));
    }
}