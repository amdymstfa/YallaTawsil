package com.yallatawsil.backend.repository;

import com.yallatawsil.backend.entity.Tour;
import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.entity.enums.TourStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Tour Repository
 * Data access layer for Tour entity
 */
public interface TourRepository extends JpaRepository<Tour, Long> {

    /**
     * Find all tours for a specific date
     * @param date the tour date
     * @return list of tours on that date
     */
    List<Tour> findByDate(LocalDate date);

    /**
     * Find all tours assigned to a specific vehicle
     * @param vehicle the vehicle
     * @return list of tours for that vehicle
     */
    List<Tour> findByVehicle(Vehicle vehicle);

    /**
     * Find tour by date and vehicle (should be unique)
     * @param date the tour date
     * @param vehicle the vehicle
     * @return Optional containing the tour if found
     */
    Optional<Tour> findByDateAndVehicle(LocalDate date, Vehicle vehicle);

    /**
     * Find all tours with a specific status
     * @param status the tour status
     * @return list of tours with that status
     */
    List<Tour> findByStatus(TourStatus status);

    /**
     * Find tours within a date range
     * @param start start date
     * @param end end date
     * @return list of tours in the range
     */
    @Query("SELECT t FROM Tour t WHERE t.date BETWEEN :start AND :end")
    List<Tour> findByDateRange(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    /**
     * Find tours by status and date range
     * @param status the tour status
     * @param start start date
     * @param end end date
     * @return list of tours matching criteria
     */
    @Query("SELECT t FROM Tour t WHERE t.status = :status AND t.date BETWEEN :start AND :end")
    List<Tour> findByStatusAndDateRange(
            @Param("status") TourStatus status,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    /**
     * Find tours optimized with a specific algorithm
     * @param algorithm the optimization algorithm name
     * @return list of tours
     */
    List<Tour> findByOptimizationAlgorithm(String algorithm);

    /**
     * Count tours by status
     * @param status the tour status
     * @return number of tours with that status
     */
    long countByStatus(TourStatus status);

    /**
     * Count tours for a specific date
     * @param date the date
     * @return number of tours on that date
     */
    long countByDate(LocalDate date);

    /**
     * Find tours with total distance greater than specified value
     * @param distance minimum distance in km
     * @return list of tours
     */
    List<Tour> findByTotalDistanceGreaterThan(Double distance);

    /**
     * Get average distance by optimization algorithm
     * @param algorithm the algorithm name
     * @return average distance in km
     */
    @Query("SELECT AVG(t.totalDistance) FROM Tour t WHERE t.optimizationAlgorithm = :algorithm")
    Double getAverageDistanceByAlgorithm(@Param("algorithm") String algorithm);

    /**
     * Find tours for a specific vehicle within a date range
     * @param vehicle the vehicle
     * @param start start date
     * @param end end date
     * @return list of tours
     */
    @Query("SELECT t FROM Tour t WHERE t.vehicle = :vehicle AND t.date BETWEEN :start AND :end")
    List<Tour> findByVehicleAndDateRange(
            @Param("vehicle") Vehicle vehicle,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    /**
     * Check if a vehicle is already assigned to a tour on a specific date
     * @param vehicle the vehicle
     * @param date the date
     * @return true if vehicle is already assigned, false otherwise
     */
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
            "FROM Tour t WHERE t.vehicle = :vehicle AND t.date = :date")
    boolean isVehicleAssignedOnDate(
            @Param("vehicle") Vehicle vehicle,
            @Param("date") LocalDate date
    );
}