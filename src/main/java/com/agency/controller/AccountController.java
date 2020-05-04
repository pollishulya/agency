package com.agency.controller;

import com.agency.dto.AccountDto;
import com.agency.entity.Role;
import com.agency.mapper.AccountMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.RoleRepository;
import com.agency.service.AccountService;
import com.agency.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@Slf4j
@Transactional
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountController(AccountService accountService, AccountRepository accountRepository,
                             AccountMapper accountMapper, RoleRepository roleRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "/saveAfterUpdate")
    @Transactional
    public ResponseEntity updateAccount(@RequestBody AccountDto accountDto) {

        if (accountDto.getFirstname().equals("") || accountDto.getEmail().equals("") || accountDto.getPhone().equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            List<Account> account = accountRepository.findAccountByEmailOrPhone(accountDto.getEmail(), accountDto.getPhone());
            if (account.size() == 1) {
                return accountService.updateAccount(accountDto);
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findAccounts", produces = "application/json")
    @ResponseBody
    public List<AccountDto> findAccounts(@RequestParam String param, @RequestParam int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Account> accounts;
        if ("".equals(param)) {
            accounts = accountRepository.findAll(pageable);
        } else {
            accounts = accountRepository.findAccounts(param, pageable);
        }

        List<AccountDto> accountsDto = new ArrayList<>();
        for (Account account : accounts) {
            accountsDto.add(accountMapper.toDto(account));
        }
        return accountsDto;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteAccount(@PathVariable Long id) {

        Long currentUserId = getCurrentUserId();
        if (currentUserId != id) {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/update/{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public AccountDto updateAccount(@PathVariable Long id) {

        AccountDto account = accountMapper.toDto(accountRepository.findById(id).get());

        return account;
    }

    @GetMapping(value = "/settings", produces = "application/json")
    public ResponseEntity updateAccountViaUser() {

        Long id = getCurrentUserId();

        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            AccountDto accountDto = accountMapper.toDto(account.get());
            return new ResponseEntity(accountDto, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/settings/save", produces = "application/json")
    @Transactional
    public ResponseEntity saveAccountUpdateViaUser(@RequestBody AccountDto accountDto) {

        if (accountDto.getFirstname().equals("") || accountDto.getEmail().equals("") || accountDto.getPhone().equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            List<Account> account = accountRepository.findAccountByEmailOrPhone(accountDto.getEmail(), accountDto.getPhone());
            if (account.size() == 1) {
                Collection<SimpleGrantedAuthority> nowAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                        .getContext().getAuthentication().getAuthorities();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accountDto.getEmail(), accountDto.getPassword(), nowAuthorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return  accountService.updateAccount(accountDto);
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/showAccounts")
    public ModelAndView showAccounts() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("account/accountsTablePage");

        return modelAndView;
    }

    @GetMapping(value = "/roles", produces = "application/json")
    @ResponseBody
    public List<Role> findRoles() {

        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    private Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }
}
