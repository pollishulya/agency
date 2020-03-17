package com.agency.service;

import com.agency.dto.AccountDto;
//import com.agency.entity.Role;
//import com.agency.enums.Roles;
import com.agency.enums.Roles;
import com.agency.mapper.AccountMapper;
import com.agency.repository.AccountRepository;
//import com.agency.repository.RoleRepository;
import com.agency.entity.Account;
import com.agency.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleStatus;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Transactional
//@Slf4j
//@Service
//public class AccountService {
//
//    private final AccountRepository accountRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    // private final RoleRepository roleRepository;
//    private final AccountMapper accountMapper;
//
//    @Autowired
//    public AccountService(BCryptPasswordEncoder bCryptPasswordEncoder,
//            /*                         RoleRepository roleRepository,*/AccountRepository accountRepository, AccountMapper accountMapper) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        //this.roleRepository = roleRepository;
//        this.accountRepository = accountRepository;
//        this.accountMapper = accountMapper;
//    }
//
//
//    @Bean
//    public AccountDto createAccount(AccountDto user) {
//
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//           setRolesForuser(user, Roles.ROLE_USER);
//
//        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(user)));
//    }
//
//    public ResponseEntity updateAccount(AccountDto accountDto) {
//
//        Optional<Account> account=accountRepository.findById(accountDto.getId());
//
//
//        if(account.isPresent()){
//
//            String password =account.get().getPassword();
//
//            accountDto.setPassword(password);
//            //  setRolesForuser(userDto, Roles.valueOf(userDto.getRole()));
//            Account updatedaccount= accountRepository.saveAndFlush(accountMapper.toEntity(accountDto));
//
//            return new ResponseEntity(accountMapper.toDto(updatedaccount),HttpStatus.OK);
//        }
//
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//
//    public void setRolesForuser(AccountDto user) {
//
//        //Set<Role> roles = new HashSet<>();
//      //  roles.add(roleRepository.findByRole(role));
//
//        user.setRoleSet(roles);
//    }
//
//    public void changePassword(Account account, String password) {
//
//        account.setPassword(bCryptPasswordEncoder.encode(password));
//        accountRepository.saveAndFlush(account);
//
//        log.info("IN UserService changeUserPassword for user {}", account);
//
//    }
//}
@Transactional
@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDto createAccount(AccountDto account) {

        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
       // setRolesForAccount(account, Roles.ROLE_USER);

        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(account)));
    }

    public ResponseEntity updateAccount(AccountDto accountDto) {

        Optional<Account> account=accountRepository.findById(accountDto.getId());


        if(account.isPresent()){

            String password =account.get().getPassword();

            accountDto.setPassword(password);
          //  setRolesForAccount(accountDto, Roles.valueOf(accountDto.getAccess()));
            Account updatedAccount = accountRepository.saveAndFlush(accountMapper.toEntity(accountDto));

            return new ResponseEntity(accountMapper.toDto(updatedAccount),HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


   /* public void setRolesForAccount(AccountDto account, Roles role) {

       // Set<Role> roles = new HashSet<>();

       // roles.add(roleRepository.findByRole(role));

      //  account.setAccess(role);
    }
*/
    public void changePassword(Account account, String password) {

        account.setPassword(bCryptPasswordEncoder.encode(password));
        accountRepository.saveAndFlush(account);

        log.info("IN UserService changeUserPassword for user {}", account);

    }
}
