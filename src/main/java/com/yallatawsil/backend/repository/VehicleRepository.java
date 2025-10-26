package com.yallatawsil.backend.repository;

import com.yallatawsil.backend.entity.Vehicle;
import com.yallatawsil.backend.entity.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Vehicle Repository
 * Data access layer for Vehicle entity
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    /**
     * Find vehicle by license plate
     * @param licensePlate the license plate to search for
     * @return Optional containing the vehicle if found
     */
    Optional<Vehicle> findByLicensePlate(String licensePlate);

    /**
     * Find all vehicles of a specific type
     * @param type the vehicle type
     * @return list of vehicles of the specified type
     */
    List<Vehicle> findByType(VehicleType type);

    /**
     * Find all available vehicles for a specific date
     * A vehicle is available if it's not assigned to any tour on that date
     * @param date the date to check availability
     * @return list of available vehicles
     */
    @Query("SELECT v FROM Vehicle v WHERE v.id NOT IN " +
            "(SELECT t.vehicle.id FROM Tour t WHERE t.date = :date)")
    List<Vehicle> findAvailableVehicles(@Param("date") LocalDate date);

    /**
     * Check if a vehicle exists by license plate
     * @param licensePlate the license plate to check
     * @return true if vehicle exists, false otherwise
     */
    boolean existsByLicensePlate(String licensePlate);

    /**
     * Count vehicles by type
     * @param type the vehicle type
     * @return number of vehicles of the specified type
     */
    long countByType(VehicleType type);
}