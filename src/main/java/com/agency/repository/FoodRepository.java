package com.agency.repository;

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
public interface FoodRepository extends JpaRepository<Food, Long> {

    Food findFirstById(Long id);

  /*  Optional<Tour> findFirstByName(String name);

    Page<Tour> findAllByCompanyId(Long id, Pageable pageable);

    List<Tour> findAllByType(String type);

    @Query("SELECT tour FROM Tour tour WHERE tour.name LIKE CONCAT('%', :string, '%')" +
            " or tour.country LIKE CONCAT('%', :string, '%') or tour.exitDate LIKE CONCAT('%', :string, '%')" +
            "or tour.numberDays LIKE CONCAT('%', :string, '%') or tour.cost LIKE CONCAT('%', :string, '%')" +
            "or tour.rating LIKE CONCAT('%', :string, '%')or tour.type LIKE CONCAT('%', :string, '%')")
    Page<Tour> findTours(@Param("string") String param, Pageable pageable);*/

}
