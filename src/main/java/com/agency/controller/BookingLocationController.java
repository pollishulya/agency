package com.agency.controller;

import com.agency.dto.BookingLocationDto;

import com.agency.entity.Account;
import com.agency.entity.BookingLocation;

import com.agency.mapper.BookingLocationMapper;

import com.agency.repository.AccountRepository;
import com.agency.repository.BookingLocationRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class BookingLocationController {

    private final BookingLocationRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final BookingLocationMapper reservationMapper;

    @Autowired
    public BookingLocationController(BookingLocationRepository reservationRepository, AccountRepository accountRepository, BookingLocationMapper reservationMapper) {

        this.reservationRepository = reservationRepository;
        this.accountRepository = accountRepository;
        this.reservationMapper = reservationMapper;
    }


    @PostMapping(value = "/location/bookingLocation")
    @Transactional
    public ResponseEntity reserveLocation(@RequestBody BookingLocationDto reservationDto) {

        Long id = getCurrentUserId();
        reservationDto.setAccountId(id);
        BookingLocation reservation = reservationMapper.toEntity(reservationDto);
        log.info(reservation.getUsername());
        reservationRepository.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bookingLocation/load", produces = "application/json")
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
   /* @GetMapping(value = "/orders")
    public ModelAndView showOrders() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("userReservationPage");

        return modelAndView;
    }

    @GetMapping(value = "/cancel/{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public BookingProgramDto cancelRecord(@PathVariable Long id) {
        BookingProgramDto account = reservationMapper.toDto(reservationRepository.findById(id).get());

        return account;
    }*/


    @GetMapping(value = "/bookingLocation/loadCompany", produces = "application/json")
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
