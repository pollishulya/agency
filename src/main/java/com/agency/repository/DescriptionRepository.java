package com.agency.repository;

import com.agency.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescriptionRepository extends JpaRepository<Description,Long> {

    List<Description> findAllByFoodId(Long idFood);
    List<Description> findAllByLocationId(Long idLocation);
    List<Description> findAllByProgramId(Long idProgram);
}
