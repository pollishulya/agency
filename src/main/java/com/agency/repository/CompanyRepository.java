package com.agency.repository;

import com.agency.entity.FoodCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<FoodCompany,Long> {}
