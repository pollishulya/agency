package com.agency.service;

import com.agency.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Slf4j
@Service
public class FoodCompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public FoodCompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }



}
