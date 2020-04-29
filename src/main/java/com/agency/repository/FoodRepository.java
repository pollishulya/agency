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

    Optional<Food> findFirstByName(String name);

    Page<Food> findAllByCompanyId(Long id, Pageable pageable);

    List<Food> findAllByType(String type);

    List<Food> findAllByCuisine(String cuisine);

    @Query("SELECT food FROM Food food WHERE food.name LIKE CONCAT('%', :string, '%')")
    Page<Food> findFoods(@Param("string") String param, Pageable pageable);

}
