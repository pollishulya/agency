package com.agency.repository;

import com.agency.entity.BookingLocation;
import com.agency.entity.BookingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingLocationRepository extends JpaRepository<BookingLocation,Long> {

    List<BookingLocation> findAllByAccountId(Long id);
    List<BookingLocation>findAllByCompanyId(Long id);
    List<BookingLocation> findByLocationIdAndDate(Long id, Date date);

}
