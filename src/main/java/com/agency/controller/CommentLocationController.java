package com.agency.controller;

import com.agency.dto.CommentDto;
import com.agency.dto.CommentLocationDto;
import com.agency.entity.Account;
import com.agency.entity.Comment;
import com.agency.entity.CommentLocation;
import com.agency.mapper.CommentLocationMapper;
import com.agency.mapper.CommentMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentLocationRepository;
import com.agency.repository.CommentRepository;
import com.agency.service.CommentLocationService;
import com.agency.service.CommentService;
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
@RequestMapping("/commentLocation")
@Slf4j
public class CommentLocationController {

    private final CommentLocationRepository commentRepository;
    private final AccountRepository accountRepository;
    private final CommentLocationMapper commentMapper;
    private final CommentLocationService commentService;

    @Autowired
    public CommentLocationController(CommentLocationRepository commentRepository, AccountRepository accountRepository,
                                     CommentLocationMapper commentMapper, CommentLocationService commentService) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity saveComment(@RequestBody CommentLocationDto commentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);

       commentDto.setUsername(account.getName());
        commentDto.setAccountId(account.getId());

        Optional<CommentLocation> previousComment = commentRepository.findByAccount_IdAndLocationId(account.getId(), commentDto.getLocationId());
        if (!previousComment.isPresent()) {

            CommentLocationDto comment = commentService.save(commentDto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        CommentLocationDto previousCommentDto = commentMapper.toDto(previousComment.get());

        return new ResponseEntity<>(previousCommentDto, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/comments", produces = "application/json")
    @Transactional
    @ResponseBody
    public ResponseEntity loadComments(@RequestParam Long locationId, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CommentLocation> comments = commentRepository.findAllByLocationId(locationId, pageable);
        if (!comments.isEmpty()) {
            List<CommentLocationDto> commentsDto = new ArrayList<>();

            for (CommentLocation comment : comments) {
                commentsDto.add(commentMapper.toDto(comment));
            }
            return new ResponseEntity<>(commentsDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/update/save")
    public ResponseEntity updateComment(@RequestBody CommentLocationDto commentDto) {

        CommentLocationDto updatedComment = commentService.update(commentDto);

        return new ResponseEntity(updatedComment, HttpStatus.OK);

    }

}
