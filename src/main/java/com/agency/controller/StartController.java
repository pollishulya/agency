package com.agency.controller;


import com.agency.dto.AccountDto;
import com.agency.entity.Account;
import com.agency.repository.AccountRepository;

import com.agency.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

@RestController
@Slf4j
public class StartController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Autowired
   public StartController(AccountService accountService, AccountRepository accountRepository) {

        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/403")
    public ModelAndView deniedAccess() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("403");

        return modelAndView;
    }

    @GetMapping(value = "/enter")
    public ModelAndView showFoods() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("account/enter");


        return modelAndView;
    }


    @GetMapping(value = "/")
    public ModelAndView start(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", "login.error");
        }

       if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }
        modelAndView.setViewName("blocks/welcomePage");

        return modelAndView;

    }

    @PostMapping(value = "/registration")
    public ResponseEntity registration(@RequestBody AccountDto accountDto) {

     //   if (accountDto.getFirstname().equals("") || accountDto.getEmail().equals("") || accountDto.getPhone().equals("")) {
       //     return new ResponseEntity<>("Empty fields error", HttpStatus.BAD_REQUEST);
        //} else {
          //  List<Account> account = accountRepository.findAccountByEmailOrPhone(accountDto.getEmail(), accountDto.getPhone());
           //if (account.size() == 0) {

               accountService.createAccount(accountDto);
                return  new ResponseEntity<>(HttpStatus.OK);
            //}
            //return new ResponseEntity<>("Unique fields error", HttpStatus.INTERNAL_SERVER_ERROR);
        //}

    }


}