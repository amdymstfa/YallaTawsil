package com.yallatawsil.backend.repository;

import com.yallatawsil.backend.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Warehouse Repository
 * Data access layer for Warehouse entity
 */
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    /**
     * Find warehouse by name
     * @param name the warehouse name
     * @return Optional containing the warehouse if found
     */
    Optional<Warehouse> findByName(String name);

    /**
     * Find warehouses by partial name match (case-insensitive)
     * @param name the partial name to search
     * @return list of matching warehouses
     */
    @Query("SELECT w FROM Warehouse w WHERE LOWER(w.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Warehouse> findByNameContaining(@Param("name") String name);

    /**
     * Find warehouses within a geographical area (simple box query)
     * @param minLat minimum latitude
     * @param maxLat maximum latitude
     * @param minLon minimum longitude
     * @param maxLon maximum longitude
     * @return list of warehouses within the area
     */
    @Query("SELECT w FROM Warehouse w WHERE " +
            "w.latitude BETWEEN :minLat AND :maxLat AND " +
            "w.longitude BETWEEN :minLon AND :maxLon")
    List<Warehouse> findWarehousesInArea(
            @Param("minLat") Double minLat,
            @Param("maxLat") Double maxLat,
            @Param("minLon") Double minLon,
            @Param("maxLon") Double maxLon
    );

    /**
     * Check if a warehouse exists by name
     * @param name the warehouse name
     * @return true if warehouse exists, false otherwise
     */
    boolean existsByName(String name);
}