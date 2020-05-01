package com.agency.service;

import com.agency.dto.CommentLocationDto;
import com.agency.entity.Account;
import com.agency.entity.Comment;
import com.agency.entity.CommentLocation;
import com.agency.entity.Location;
import com.agency.mapper.CommentLocationMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentLocationRepository;
import com.agency.repository.LocationRepository;

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
public class CommentLocationService {

    private final CommentLocationRepository commentRepository;
    private final LocationRepository foodRepository;
    private final CommentLocationMapper commentMapper;
    private final AccountRepository accountRepository;

    @Autowired
    public CommentLocationService(CommentLocationRepository commentRepository, LocationRepository foodRepository, CommentLocationMapper commentMapper,
                                  AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.foodRepository = foodRepository;
        this.commentMapper = commentMapper;
        this.accountRepository = accountRepository;
    }

    public CommentLocationDto save(CommentLocationDto commentDto) {

        commentDto.setTime(setDate());
        CommentLocation comment = commentRepository.save(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    public CommentLocationDto update(CommentLocationDto commentDto) {

        Long id = getCurrentUserId();

        commentDto.setAccountId(id);
        commentDto.setTime(setDate());

        CommentLocation comment = commentRepository.saveAndFlush(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    private void recountRating(CommentLocationDto comment) {

        Location food = foodRepository.findFirstById(comment.getLocationId());
        if(food.getRating()==-1){
            food.setRating(comment.getRating());
        }

        int rating = food.getRating();

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
}
