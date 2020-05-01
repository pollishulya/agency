package com.agency.controller;

import com.agency.entity.Company;
import com.agency.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/company")
public class FoodCompanyController {


    private final CompanyService companyService;

    @Autowired
    public FoodCompanyController(CompanyService foodCompanyService) {
        this.companyService = foodCompanyService;
    }


    @RequestMapping(value = "/addingCompany", method = {RequestMethod.GET})
    public ModelAndView addingCompany(Company foodCompany) {

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addingCompany");

        return modelAndView;
    }

    @RequestMapping(value = "/addingCompany", method = {RequestMethod.POST})
    public ModelAndView addingCompanyPost(Company company) {

        ModelAndView modelAndView=new ModelAndView("startPage");

      //  companyService.createCompany(company);

        return modelAndView;
    }

}
