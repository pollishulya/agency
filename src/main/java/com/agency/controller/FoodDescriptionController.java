package com.agency.controller;

import com.agency.dto.FoodDescriptionDto;
import com.agency.entity.FoodDescription;
import com.agency.mapper.FoodDescriptionMapper;
import com.agency.repository.FoodDescriptionRepository;
import com.agency.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

@RestController
@RequestMapping("/description")
@Slf4j
public class FoodDescriptionController {

    private final FoodDescriptionRepository foodDescriptionRepository;
    private final FoodDescriptionMapper foodDescriptionMapper;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodDescriptionController(FoodDescriptionRepository foodDescriptionRepository, FoodDescriptionMapper foodDescriptionMapper,
                                     FoodRepository foodRepository) {
        this.foodDescriptionRepository = foodDescriptionRepository;
        this.foodDescriptionMapper = foodDescriptionMapper;
        this.foodRepository = foodRepository;
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        foodDescriptionRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity load(@RequestParam Long foodId) {

        List<FoodDescription> descriptions = foodDescriptionRepository.findAllByFoodId(foodId);
        List<FoodDescriptionDto> descriptionsDto = new ArrayList<>();
        if (!descriptions.isEmpty()) {
            for (FoodDescription description : descriptions) {
                descriptionsDto.add(foodDescriptionMapper.toDto(description));
            }
            descriptionsDto.sort(comparing(FoodDescriptionDto::getDayNumber));
            return new ResponseEntity<>(descriptionsDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity save(@RequestBody List<FoodDescriptionDto> foodDescription) {

//        Optional<Tour> tourOptional;
//
//        for (TourDescriptionDto tourDescriptionDto : tourDescription) {
//            tourOptional = tourRepository.findFirstByName(tourDescriptionDto.getTourName());
//            if(tourOptional.isPresent()){
//                tourDescriptionDto.setTourId(tourOptional.get().getId());
//                tourDescriptionRepository.save(tourDescriptionMapper.toEntity(tourDescriptionDto));
//
//            }
//
//        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(value = "/update")
    @Transactional
    public ResponseEntity update(@RequestBody List<FoodDescriptionDto> foodDescriptionList) {

        for (FoodDescriptionDto foodDescriptionDto : foodDescriptionList) {

            foodDescriptionRepository.saveAndFlush(foodDescriptionMapper.toEntity(foodDescriptionDto));
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
