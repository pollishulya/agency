package com.agency.controller;

import com.agency.dto.BookingLocationDto;

import com.agency.dto.LocationDto;
import com.agency.entity.Account;
import com.agency.entity.BookingLocation;

import com.agency.mapper.BookingLocationMapper;

import com.agency.repository.AccountRepository;
import com.agency.repository.BookingLocationRepository;

import com.agency.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@Slf4j
public class BookingLocationController {

    private final BookingLocationRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final BookingLocationMapper reservationMapper;
    private final LocationService locationService;

    @Autowired
    public BookingLocationController(BookingLocationRepository reservationRepository, AccountRepository accountRepository,
                                     BookingLocationMapper reservationMapper,
                                     LocationService locationService) {

        this.reservationRepository = reservationRepository;
        this.accountRepository = accountRepository;
        this.reservationMapper = reservationMapper;
        this.locationService=locationService;
    }


    @PostMapping(value = "/location/bookingLocation")
    @Transactional
    public ResponseEntity reserveLocation(@RequestBody BookingLocationDto reservationDto) {

        Long id = getCurrentUserId();
        reservationDto.setAccountId(id);
        BookingLocation reservation = reservationMapper.toEntity(reservationDto);
        log.info(reservation.getUsername());
        List<BookingLocation> previousDate = reservationRepository.findByLocationIdAndDate(reservationDto.getLocationId(), reservationDto.getDate());
        if (reservation.getPhone().equals("") ||reservation.getUsername().equals("")) {
            return new ResponseEntity("empty field",HttpStatus.BAD_REQUEST);

        }
        else if(!reservation.getDate().after(new Date())){
            return new ResponseEntity("data error",HttpStatus.BAD_REQUEST);
        } else if (!previousDate.isEmpty()) {
            return new ResponseEntity("data booked",HttpStatus.BAD_REQUEST);

        }
        else {
            reservationRepository.save(reservation);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/bookingLocation/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteBookingLocation(@PathVariable Long id) {

        try {
            reservationRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(value = "/reservation/loadLocation", produces = "application/json")
    @ResponseBody
    @Transactional
    public List<BookingLocationDto> userOrders() {

        Long id = getCurrentUserId();
        List<BookingLocation> reservations = reservationRepository.findAllByAccountId(id);
        List<BookingLocationDto> reservationsDto = new ArrayList<>();
        for (BookingLocation reservation : reservations) {
            reservationsDto.add(reservationMapper.toDto(reservation));
        }

        return reservationsDto;
    }

    @RequestMapping(value = "/bookingLocation/cancel/{id}", method = {RequestMethod.POST})
    public ResponseEntity cancelBookingLocation(@RequestBody BookingLocationDto location) {

        locationService.cancel(location);

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/reservation/loadCompanyLocation", produces = "application/json")
    @ResponseBody
    @Transactional
    public List<BookingLocationDto> companyOrders() {

        Long id = getCurrentUserId();
        List<BookingLocation> reservations = reservationRepository.findAllByCompanyId(id);

        List<BookingLocationDto> reservationsDto = new ArrayList<>();
        for (BookingLocation reservation : reservations) {
            reservationsDto.add(reservationMapper.toDto(reservation));
        }

        return reservationsDto;
    }

   /* @GetMapping(value = "/ordersCompany")
    public ModelAndView showOrdersCompany() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("companyReservationPage");

        return modelAndView;
    }*/

    private Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }



}
