package com.agency.controller;

import com.agency.dto.BookingProgramDto;
import com.agency.dto.ReservationDto;
import com.agency.entity.Account;
import com.agency.entity.BookingProgram;
import com.agency.entity.Reservation;
import com.agency.mapper.BookingProgramMapper;
import com.agency.mapper.ReservationMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.BookingProgramRepository;
import com.agency.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class BookingProgramController {

    private final BookingProgramRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final BookingProgramMapper reservationMapper;

    @Autowired
    public BookingProgramController(BookingProgramRepository reservationRepository, AccountRepository accountRepository, BookingProgramMapper reservationMapper) {

        this.reservationRepository = reservationRepository;
        this.accountRepository = accountRepository;
        this.reservationMapper = reservationMapper;
    }


    @PostMapping(value = "/program/bookingProgram")
    @Transactional
    public ResponseEntity reserveProgram(@RequestBody BookingProgramDto reservationDto) {

        Long id = getCurrentUserId();
        reservationDto.setAccountId(id);
        BookingProgram reservation = reservationMapper.toEntity(reservationDto);
        log.info(reservation.getUsername());
        reservationRepository.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bookingProgram/load", produces = "application/json")
    @ResponseBody
    @Transactional
    public List<BookingProgramDto> userOrders() {

        Long id = getCurrentUserId();
        List<BookingProgram> reservations = reservationRepository.findAllByAccountId(id);
        List<BookingProgramDto> reservationsDto = new ArrayList<>();
        for (BookingProgram reservation : reservations) {
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


    @GetMapping(value = "/bookingProgram/loadCompany", produces = "application/json")
    @ResponseBody
    @Transactional
    public List<BookingProgramDto> companyOrders() {

        Long id = getCurrentUserId();
        List<BookingProgram> reservations = reservationRepository.findAllByCompanyId(id);

        List<BookingProgramDto> reservationsDto = new ArrayList<>();
        for (BookingProgram reservation : reservations) {
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
