package com.agency.service;

import com.agency.dto.FoodDto;
import com.agency.entity.Account;
import com.agency.entity.Food;
import com.agency.mapper.FoodMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.DescriptionRepository;
import com.agency.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final  FoodMapper foodMapper;
    private final AccountRepository accountRepository;
    private final  DescriptionRepository foodDescriptionRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper, AccountRepository accountRepository, DescriptionRepository foodDescriptionRepository) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
        this.accountRepository = accountRepository;
        this.foodDescriptionRepository = foodDescriptionRepository;
    }


    public ResponseEntity save(Food food) {

        Optional<Food> tourOptional = foodRepository.findFirstByName(food.getName());
        if (!tourOptional.isPresent()) {
            Long id = getCurrentUserId();

            food.setRating(-1);
            food.setCompany(accountRepository.findById(id).get());
            food.setImage("/resources/images/imageForFood");

            if (food.getFoodDescriptions() != null) {
                food.getFoodDescriptions().forEach(td -> td.setFood(food));
            }

            Food savedTour = foodRepository.saveAndFlush(food);

            return new ResponseEntity(savedTour,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public FoodDto update(FoodDto foodDto) {

        foodDto.setCompanyId(getCurrentUserId());
       foodDto.setRating(3);
        foodDto.setImage("/resources/images/imageForFood");

        Food food = foodRepository.saveAndFlush(foodMapper.toEntity(foodDto));
        return foodMapper.toDto(food);
    }

    public ResponseEntity getAll(Pageable pageable, String param) {

        Page<Food> foods;
        Long id = getCurrentUserId();

        if ("".equals(param)) {
            foods =foodRepository.findAllByCompanyId(id, pageable);
        } else {
            foods = foodRepository.findFoods(param, pageable);
        }

        if (foods.getSize() != 0) {
            List<FoodDto> toursDto = new ArrayList<>();
            for (Food food : foods) {
                toursDto.add(foodMapper.toDto(food));
            }

            return new ResponseEntity(toursDto, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    public Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }
}