package com.agency.service;


import com.agency.dto.CommentProgramDto;
import com.agency.entity.*;
import com.agency.mapper.CommentLocationMapper;
import com.agency.mapper.CommentProgramMapper;
import com.agency.repository.*;
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
public class CommentProgramService {

    private final CommentProgramRepository commentRepository;
    private final CommentProgramMapper commentMapper;
    private final AccountRepository accountRepository;
    private final ProgramRepository programRepository;

    @Autowired
    public CommentProgramService(CommentProgramRepository commentRepository, CommentProgramMapper commentMapper,
                          AccountRepository accountRepository,
                          ProgramRepository programRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.accountRepository = accountRepository;
        this.programRepository=programRepository;
    }

    public CommentProgramDto save(CommentProgramDto commentDto) {

        commentDto.setTime(setDate());
        CommentProgram comment = commentRepository.save(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    public CommentProgramDto update(CommentProgramDto commentDto) {

        Long id = getCurrentUserId();

        commentDto.setAccountId(id);
        commentDto.setTime(setDate());

        CommentProgram comment = commentRepository.saveAndFlush(commentMapper.toEntity(commentDto));
        recountRating(commentDto);

        return commentMapper.toDto(comment);
    }

    private void recountRating(CommentProgramDto comment) {

        Program food = programRepository.findFirstById(comment.getProgramId());
        if(food.getRating()==-1){
            food.setRating(comment.getRating());
        }

        float rating = food.getRating();

        rating = Math.round((rating + comment.getRating()) / 2);
        food.setRating((int) rating);
        programRepository.saveAndFlush(food);
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
