package com.agency.repository;

import com.agency.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agency.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  LocationRepository extends JpaRepository<Location, Long> {

    Location findFirstById(Long id);

    Optional<Location> findFirstByName(String name);

    Page<Location> findAllByCompanyId(Long id, Pageable pageable);

    // List<Food> findAllByType(String type);

    @Query("SELECT location FROM Location location WHERE location.name LIKE CONCAT('%', :string, '%')")
    Page<Location> findLocations(@Param("string") String param, Pageable pageable);

}
