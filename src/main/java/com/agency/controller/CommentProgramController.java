package com.agency.controller;

import com.agency.dto.CommentLocationDto;
import com.agency.dto.CommentProgramDto;
import com.agency.entity.Account;
import com.agency.entity.CommentLocation;
import com.agency.entity.CommentProgram;
import com.agency.mapper.CommentLocationMapper;
import com.agency.mapper.CommentProgramMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentLocationRepository;
import com.agency.repository.CommentProgramRepository;
import com.agency.service.CommentLocationService;
import com.agency.service.CommentProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commentProgram")
@Slf4j
public class CommentProgramController {

    private final CommentProgramRepository commentRepository;
    private final AccountRepository accountRepository;
    private final CommentProgramMapper commentMapper;
    private final CommentProgramService commentService;

    @Autowired
    public CommentProgramController(CommentProgramRepository commentRepository, AccountRepository accountRepository,
                                    CommentProgramMapper commentMapper, CommentProgramService commentService) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }
    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity saveComment(@RequestBody CommentProgramDto commentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);

       commentDto.setUsername(account.getName());
        commentDto.setAccountId(account.getId());

        Optional<CommentProgram> previousComment = commentRepository.findByAccount_IdAndProgramId(account.getId(), commentDto.getProgramId());
        if (!previousComment.isPresent()) {

            CommentProgramDto comment = commentService.save(commentDto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        CommentProgramDto previousCommentDto = commentMapper.toDto(previousComment.get());

        return new ResponseEntity<>(previousCommentDto, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/comments", produces = "application/json")
    @Transactional
    @ResponseBody
    public ResponseEntity loadComments(@RequestParam Long programId, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CommentProgram> comments = commentRepository.findAllByProgramId(programId, pageable);
        if (!comments.isEmpty()) {
            List<CommentProgramDto> commentsDto = new ArrayList<>();

            for (CommentProgram comment : comments) {
                commentsDto.add(commentMapper.toDto(comment));
            }
            return new ResponseEntity<>(commentsDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/update/save")
    public ResponseEntity updateComment(@RequestBody CommentProgramDto commentDto) {

        CommentProgramDto updatedComment = commentService.update(commentDto);

        return new ResponseEntity(updatedComment, HttpStatus.OK);

    }

}
