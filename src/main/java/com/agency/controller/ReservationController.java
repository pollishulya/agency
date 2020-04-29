package com.agency.controller;

import com.agency.dto.ReservationDto;
import com.agency.entity.Account;
import com.agency.entity.Reservation;
import com.agency.mapper.ReservationMapper;
import com.agency.repository.AccountRepository;
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
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, AccountRepository accountRepository, ReservationMapper reservationMapper) {

        this.reservationRepository = reservationRepository;
        this.accountRepository = accountRepository;
        this.reservationMapper = reservationMapper;
    }


    @PostMapping(value = "/food/reservation")
    @Transactional
    public ResponseEntity reserveFood(@RequestBody ReservationDto reservationDto) {

        Long id = getCurrentUserId();
        reservationDto.setAccountId(id);
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        log.info(reservation.getUsername());
        reservationRepository.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/location/reservation")
    @Transactional
    public ResponseEntity reserveLocation(@RequestBody ReservationDto reservationDto) {

        Long id = getCurrentUserId();
        reservationDto.setAccountId(id);
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        log.info(reservation.getUsername());
        reservationRepository.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/reservation/load", produces = "application/json")
    @ResponseBody
    @Transactional
    public List<ReservationDto> userOrders() {

        Long id = getCurrentUserId();
        List<Reservation> reservations = reservationRepository.findAllByAccountId(id);

        List<ReservationDto> reservationsDto = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationsDto.add(reservationMapper.toDto(reservation));
        }

        return reservationsDto;
    }
    @GetMapping(value = "/orders")
    public ModelAndView showOrders() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("usReservationPage");

        return modelAndView;
    }

    @GetMapping(value = "/reservation/loadCompany", produces = "application/json")
    @ResponseBody
    @Transactional
    public List<ReservationDto> companyOrders() {

        Long id = getCurrentUserId();
        List<Reservation> reservations = reservationRepository.findAllByCompanyId(id);

        List<ReservationDto> reservationsDto = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationsDto.add(reservationMapper.toDto(reservation));
        }

        return reservationsDto;
    }

    @GetMapping(value = "/ordersCompany")
    public ModelAndView showOrdersCompany() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("companyReservationPage");

        return modelAndView;
    }

    private Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }

    @GetMapping(value = "/pageLocation")
    public ModelAndView pageLocation() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("pageLocation");

        return modelAndView;
    }

    @GetMapping(value = "/pageMenu")
    public ModelAndView pageMenu() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("pageMenu");

        return modelAndView;
    }

    @GetMapping(value = "/pageTamada")
    public ModelAndView pageTamada() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("pageTamada");

        return modelAndView;
    }

    @GetMapping(value = "/companyOrders")
    public ModelAndView showCompanyOrders() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("companyReservationPage");

        return modelAndView;
    }


}
