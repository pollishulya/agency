package com.agency.service;

<<<<<<< HEAD
import com.agency.dto.CommentDto;
import com.agency.entity.Account;
import com.agency.entity.Comment;
import com.agency.entity.Food;
import com.agency.mapper.CommentMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentRepository;
import com.agency.repository.FoodRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Transactional
@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final FoodRepository foodRepository;
    private final CommentMapper commentMapper;
    private final AccountRepository accountRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, FoodRepository foodRepository, CommentMapper commentMapper, AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.foodRepository = foodRepository;
        this.commentMapper = commentMapper;
        this.accountRepository = accountRepository;
    }

    public CommentDto save(CommentDto commentDto) {

        commentDto.setTime(setDate());
        Comment comment = commentRepository.save(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    public CommentDto update(CommentDto commentDto) {

        Long id = getCurrentUserId();

        commentDto.setAccountId(id);
        commentDto.setTime(setDate());

        Comment comment = commentRepository.saveAndFlush(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    private void recountRating(CommentDto comment) {

        Food food = foodRepository.findFirstById(comment.getFoodId());
        if(food.getRating()==-1){
            food.setRating(comment.getRating());
        }

        float rating = food.getRating();

        rating = Math.round((rating + comment.getRating()) / 2);
        food.setRating(rating);
        foodRepository.saveAndFlush(food);
    }

    private String setDate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(date);

        return currentDate;
    }

    private Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }
=======
public class CommentService {
>>>>>>> github/master
}
