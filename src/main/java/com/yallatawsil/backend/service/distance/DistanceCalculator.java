package com.yallatawsil.backend.service.distance;

public interface DistanceCalculator {

    /**
     * Calculate distance between two GPS coordinates using Haversine formula
     * @param lat1 Latitude of first point (degrees)
     * @param lon1 Longitude of first point (degrees)
     * @param lat2 Latitude of second point (degrees)
     * @param lon2 Longitude of second point (degrees)
     * @return Distance in kilometers
     */
    double calculateDistance(double lat1, double lon1, double lat2, double lon2);
}