package com.agency.controller;

import com.agency.dto.ProgramDto;
import com.agency.entity.Program;
import com.agency.mapper.ProgramMapper;
import com.agency.repository.ProgramRepository;
import com.agency.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ProgramController {

    private final ProgramService locationService;
    private final ProgramRepository locationRepository;
    private final ProgramMapper locationMapper;

    @Autowired
    public ProgramController(ProgramService locationService, ProgramRepository locationRepository, ProgramMapper locationMapper) {

        this.locationService = locationService;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @GetMapping(value = "/program/{id}", produces = "application/json")
    @ResponseBody
    public ModelAndView loadProgram(@PathVariable Long id) {

        Optional<Program> location = locationRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(location.get());
        modelAndView.setViewName("programPage");

       /* if(food.isPresent()){
            modelAndView.addObject(food.get());
        } else {
            throw new RuntimeException("No food with id " + id);
        }*/

        return modelAndView;
    }

    @RequestMapping(value = "/program/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteProgram(@PathVariable Long id) {

        try {
            locationRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/programs", produces = "application/json")
    @ResponseBody
    public List<Program> loadPrograms() {

        List<Program> locations = locationRepository.findAll();

        return locations;
    }

  //  @GetMapping(value = "/food", produces = "application/json")
    //@ResponseBody
 /*   public List<Food> loadTypedFoods(@RequestParam String type) {

        List<Food> foods = foodRepository.findAllByType(type);

        return foods;
    }*/

    @PostMapping(value = "/program/save")
    public ResponseEntity save(@RequestBody Program location) {


      /*  if (food.getName().equals("") || food.getView().equals("") || food.getCuisine().equals("")) {
            return new ResponseEntity("empty field",HttpStatus.BAD_REQUEST);
        }*/
      /*  else if(food.getExitDate().compareTo(new Date())<0){
            return new ResponseEntity("data error",HttpStatus.BAD_REQUEST);
        }*/
      //  else {

            return locationService.save(location);
        //}
    }

    @GetMapping(value = "/programs/show", produces = "application/json")
    public ResponseEntity showPrograms(@RequestParam String param, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return locationService.getAll(pageable, param);
    }

    @GetMapping(value = "/showPrograms")
    public ModelAndView showPrograms() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
       // modelAndView.setViewName("locationsTablePage");
        modelAndView.setViewName("programsTablePage");


        return modelAndView;
    }

    @GetMapping(value = "/showPrograms/{type}")
    public ModelAndView showTypedPrograms(@PathVariable String type) {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if ("bus".equals(type)) {
            modelAndView.setViewName("busTourPage");
        } else if ("air".equals(type)) {
            modelAndView.setViewName("airTourPage");
        } else if ("cruise".equals(type)) {
            modelAndView.setViewName("cruiseTourPage");
        }

        return modelAndView;
    }

    @GetMapping(value = "/program/update/{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity loadProgramForUpdate(@PathVariable Long id) {

        Optional<Program> location= locationRepository.findById(id);
        if(location.isPresent()){
            ProgramDto locationDto = locationMapper.toDto(location.get());
            return new ResponseEntity(locationDto,HttpStatus.OK);
        }


        return new ResponseEntity(HttpStatus.NOT_FOUND);
}

    @PostMapping(value = "/program/update")
    public ResponseEntity updateProgram(@RequestBody ProgramDto location) {

        locationService.update(location);

        return new ResponseEntity(HttpStatus.OK);
    }
}
