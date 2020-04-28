package com.agency.repository;

import com.agency.entity.Account;
import com.agency.entity.Program;
import com.agency.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByAccountId(Long id);
    //List<Reservation> findAllByCompanyId(Long id);
    List<Reservation> findAllByFoodId(Long id);
   // List<Reservation> findAllByLocationId(Long id);


    //@Query("SELECT acc FROM Reservation acc WHERE acc.company_id=id")
    //List<Reservation> findCompanyOrders(Long id);
  //  Page<Program> findAllByCompanyId(Long id, Pageable pageable);

}
