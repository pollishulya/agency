package com.agency.repository;


import com.agency.entity.Account;
import com.agency.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Account, Long> {

    Account findByAccess(Roles role);
}
