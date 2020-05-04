package com.agency.controller;

import com.agency.dto.FoodDto;
import com.agency.entity.Food;
import com.agency.mapper.FoodMapper;
import com.agency.repository.FoodRepository;
import com.agency.service.FoodService;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    @Autowired
    public FoodController(FoodService foodService, FoodRepository foodRepository, FoodMapper foodMapper) {

        this.foodService = foodService;
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    @GetMapping(value = "/food/{id}", produces = "application/json")
    @ResponseBody
    public ModelAndView loadFood(@PathVariable Long id) {

        Optional<Food> food = foodRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(food.get());
        modelAndView.setViewName("food/foodPage");

        if(food.isPresent()){
            modelAndView.addObject(food.get());
        } else {
            throw new RuntimeException("No food with id " + id);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/food/delete/{id}", method = {RequestMethod.POST})
    public ResponseEntity deleteFood(@PathVariable Long id) {

        try {
            foodRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/foods", produces = "application/json")
    @ResponseBody
    public List<Food> loadFoods() {

        List<Food> foods = foodRepository.findAll();

        return foods;
    }

    @GetMapping(value = "/food", produces = "application/json")
    @ResponseBody
    public List<Food> loadTypedFoods(@RequestParam String cuisine) {

        List<Food> foods = foodRepository.findAllByCuisine(cuisine);

        return foods;
    }

    @PostMapping(value = "/food/save")
    public ResponseEntity save(@RequestBody Food food) {


        if (food.getName().equals("") || food.getType().equals("") || food.getCuisine().equals("") || food.getPrice()<0) {
            return new ResponseEntity("empty field",HttpStatus.BAD_REQUEST);
        }
         else if(food.getPrice()<0){
            return new ResponseEntity("price error",HttpStatus.BAD_REQUEST);
        }
      /*  else if(food.getExitDate().compareTo(new Date())<0){
            return new ResponseEntity("data error",HttpStatus.BAD_REQUEST);
        }*/
        else {
            return foodService.save(food);
        }
    }

    @GetMapping(value = "/foods/show", produces = "application/json")
    public ResponseEntity showFoods(@RequestParam String param, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return foodService.getAll(pageable, param);
    }

    @GetMapping(value = "/showFoods")
    public ModelAndView showFoods() {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.setViewName("food/foodsTablePage");


        return modelAndView;
    }


    @GetMapping(value = "/showFoods/{type}")
    public ModelAndView showTypedFoods(@PathVariable String type) {

        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if ("europe".equals(type)) {
            modelAndView.setViewName("food/europeCuisinePage");
        } else if ("slavic".equals(type)) {
            modelAndView.setViewName("food/slavicCuisinePage");
        } else if ("east".equals(type)) {
            modelAndView.setViewName("food/eastCuisinePage");
        } else if ("asia".equals(type)) {
            modelAndView.setViewName("food/asiaCuisinePage");
        }
        return modelAndView;
    }

    @GetMapping(value = "/food/update/{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity loadFoodForUpdate(@PathVariable Long id) {

        Optional<Food> food= foodRepository.findById(id);
        if(food.isPresent()){
            FoodDto foodDto = foodMapper.toDto(food.get());
            return new ResponseEntity(foodDto,HttpStatus.OK);
        }


        return new ResponseEntity(HttpStatus.NOT_FOUND);
}

    @PostMapping(value = "/food/update")
    public ResponseEntity updateFood(@RequestBody FoodDto food) {

        foodService.update(food);

        return new ResponseEntity(HttpStatus.OK);
    }
}
