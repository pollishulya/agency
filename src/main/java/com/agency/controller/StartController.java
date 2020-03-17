package com.agency.controller;

import com.agency.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {

    private final StartService startService;

    @Autowired
    public StartController(StartService startService) {
        this.startService = startService;
    }

    @GetMapping("/")
    public ModelAndView deniedAccess() {

        ModelAndView modelAndView = new ModelAndView();
        startService.start();
        modelAndView.setViewName("index");

        return modelAndView;
    }


}
