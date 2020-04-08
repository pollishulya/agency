package com.agency.repository;

import com.agency.entity.FoodDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDescriptionRepository extends JpaRepository<FoodDescription,Long> {

    List<FoodDescription> findAllByFoodId(Long idFood);
}
