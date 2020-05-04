package com.agency.controller;

import com.agency.dto.AccountDto;
import com.agency.dto.FoodDto;
import com.agency.dto.ReservationDto;
import com.agency.entity.Account;
import com.agency.entity.Food;
import com.agency.entity.Reservation;
import com.agency.mapper.ReservationMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.ReservationRepository;
import com.agency.service.FoodService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final ReservationMapper reservationMapper;
    private final FoodService foodService;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, AccountRepository accountRepository,
                                 ReservationMapper reservationMapper, FoodService foodService) {

        this.reservationRepository = reservationRepository;
        this.accountRepository = accountRepository;
        this.reservationMapper = reservationMapper;
        this.foodService=foodService;
    }


    @PostMapping(value = "/food/reservation")
    @Transactional
    public ResponseEntity reserveFood(@RequestBody ReservationDto reservationDto) {

        Long id = getCurrentUserId();
        reservationDto.setAccountId(id);
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        log.info(reservation.getUsername());
        List<Reservation> previousDate = reservationRepository.findByFoodIdAndDate(reservationDto.getFoodId(), reservationDto.getDate());
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
   /* @GetMapping(value = "/reserve/cancelFood//{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity cancelFood(@PathVariable Long id) {

       /* try {
            reservationRepository.saveAndFlush(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }*/

      /*  return new ResponseEntity<>(HttpStatus.OK);
    }*/


    @RequestMapping(value = "/reserve/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteBookingFood(@PathVariable Long id) {

        try {
            reservationRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

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

    @GetMapping(value = "/reservation/loadFood", produces = "application/json")
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

    @RequestMapping(value = "/bookingFood/cancel/{id}", method = {RequestMethod.POST})
    public ResponseEntity cancelBookingLocation(@RequestBody ReservationDto food) {

        foodService.cancel(food);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/orders")
    public ModelAndView showOrders() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("logic/userReservationPage");

        return modelAndView;
    }

    @GetMapping(value = "/orders/cancel", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity loadBookingFoodForUpdate(/*@PathVariable Long id*/) {
Long id= Long.valueOf(1);
        Optional<Reservation> food= reservationRepository.findById(id);
        if(food.isPresent()){
            ReservationDto foodDto = reservationMapper.toDto(food.get());
            return new ResponseEntity(foodDto,HttpStatus.OK);
        }


        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/reservation/loadCompanyFood", produces = "application/json")
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
        modelAndView.setViewName("logic/companyReservationPage");

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
        modelAndView.setViewName("location/pageLocation");

        return modelAndView;
    }

    @GetMapping(value = "/pageMenu")
    public ModelAndView pageMenu() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("food/pageMenu");

        return modelAndView;
    }

    @GetMapping(value = "/pageTamada")
    public ModelAndView pageTamada() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("program/pageTamada");

        return modelAndView;
    }

    @GetMapping(value = "/companyOrders")
    public ModelAndView showCompanyOrders() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("logic/companyReservationPage");

        return modelAndView;
    }


}
