package com.agency.controller;

import com.agency.dto.LocationDto;
import com.agency.entity.Location;
import com.agency.mapper.LocationMapper;
import com.agency.repository.LocationRepository;
import com.agency.service.LocationService;
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
public class LocationController {

    private final LocationService locationService;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationController(LocationService locationService, LocationRepository locationRepository, LocationMapper locationMapper) {

        this.locationService = locationService;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @GetMapping(value = "/location/{id}", produces = "application/json")
    @ResponseBody
    public ModelAndView loadLocation(@PathVariable Long id) {

        Optional<Location> location = locationRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(location.get());
        modelAndView.setViewName("locationPage");

       /* if(food.isPresent()){
            modelAndView.addObject(food.get());
        } else {
            throw new RuntimeException("No food with id " + id);
        }*/

        return modelAndView;
    }

    @RequestMapping(value = "/location/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteLocation(@PathVariable Long id) {

        try {
            locationRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/locations", produces = "application/json")
    @ResponseBody
    public List<Location> loadLocations() {

        List<Location> locations = locationRepository.findAll();

        return locations;
    }

   @GetMapping(value = "/location", produces = "application/json")
    @ResponseBody
    public List<Location> loadTypedLocations(@RequestParam String type) {

        List<Location> locations = locationRepository.findAllByType(type);

        return locations;
    }

    @PostMapping(value = "/location/save")
    public ResponseEntity save(@RequestBody Location location) {


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

    @GetMapping(value = "/locations/show", produces = "application/json")
    public ResponseEntity showLocations(@RequestParam String param, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return locationService.getAll(pageable, param);
    }

    @GetMapping(value = "/showLocations")
    public ModelAndView showLocations() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
       // modelAndView.setViewName("locationsTablePage");
        modelAndView.setViewName("locationsTablePage");


        return modelAndView;
    }

    @GetMapping(value = "/showLocations/{type}")
    public ModelAndView showTypedLocations(@PathVariable String type) {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if ("restaurant".equals(type)) {
            modelAndView.setViewName("restaurantLocationPage");
        } else if ("manor".equals(type)) {
            modelAndView.setViewName("manorLocationPage");
        } else if ("tent".equals(type)) {
            modelAndView.setViewName("tentLocationPage");
        } else if ("space".equals(type)) {
            modelAndView.setViewName("spaceLocationPage");
        }
           return modelAndView;
    }

    @GetMapping(value = "/location/update/{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity loadLocationForUpdate(@PathVariable Long id) {

        Optional<Location> location= locationRepository.findById(id);
        if(location.isPresent()){
            LocationDto locationDto = locationMapper.toDto(location.get());
            return new ResponseEntity(locationDto,HttpStatus.OK);
        }


        return new ResponseEntity(HttpStatus.NOT_FOUND);
}

    @PostMapping(value = "/location/update")
    public ResponseEntity updateLocation(@RequestBody LocationDto location) {

        locationService.update(location);

        return new ResponseEntity(HttpStatus.OK);
    }
}
