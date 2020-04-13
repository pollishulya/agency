package com.agency.controller;

import com.agency.entity.FoodCompany;
import com.agency.service.FoodCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/company")
public class FoodCompanyController {


    private final FoodCompanyService foodCompanyService;

    @Autowired
    public FoodCompanyController(FoodCompanyService foodCompanyService) {
        this.foodCompanyService = foodCompanyService;
    }


    @RequestMapping(value = "/addingCompany", method = {RequestMethod.GET})
    public ModelAndView addingCompany(FoodCompany foodCompany) {

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addingCompany");

        return modelAndView;
    }

    @RequestMapping(value = "/addingCompany", method = {RequestMethod.POST})
    public ModelAndView addingCompanyPost(FoodCompany foodCompany) {

        ModelAndView modelAndView=new ModelAndView("startPage");

//        travelCompanyService.createCompany(travelCompany);

        return modelAndView;
    }

}
