package com.agency.repository;

import com.agency.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    //Account findByEmail(String email);

    Page<Client> findAll(Pageable pageable);

   // @Query("SELECT c FROM Client c WHERE acc.phone LIKE CONCAT('%', :string, '%') or acc.email LIKE CONCAT('%', :string, '%') or acc.firstname LIKE CONCAT('%', :string, '%')")
    //Page<Account> findAccounts(@Param("string") String param,Pageable pageable);

 //   List<Account> findAccountByEmailOrPhone(String email, String phone);
}
