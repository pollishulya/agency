package com.agency.repository;

import com.agency.entity.BookingLocation;
import com.agency.entity.BookingProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingLocationRepository extends JpaRepository<BookingLocation,Long> {

    List<BookingLocation> findAllByAccountId(Long id);
    List<BookingLocation>findAllByCompanyId(Long id);
    List<BookingLocation> findByLocationIdAndDate(Long id, Date date);
    List<BookingLocation> findAllByLocationId(Long id);
    // List<Reservation> findAllByLocationId(Long id);


    //@Query("SELECT acc FROM Reservation acc WHERE acc.company_id=id")
    //List<Reservation> findCompanyOrders(Long id);
    //  Page<Program> findAllByCompanyId(Long id, Pageable pageable);

}
