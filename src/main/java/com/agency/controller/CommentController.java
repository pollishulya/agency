package com.agency.controller;

import com.agency.dto.CommentDto;
import com.agency.entity.Account;
import com.agency.entity.Comment;
import com.agency.mapper.CommentMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentRepository;
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
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentRepository commentRepository, AccountRepository accountRepository, CommentMapper commentMapper, CommentService commentService) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity saveComment(@RequestBody CommentDto commentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);

//        commentDto.setUsername(account.getName());
        commentDto.setAccountId(account.getId());

        Optional<Comment> previousComment = commentRepository.findByAccount_IdAndFoodId(account.getId(), commentDto.getFoodId());
        if (!previousComment.isPresent()) {

            CommentDto comment = commentService.save(commentDto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        CommentDto previousCommentDto = commentMapper.toDto(previousComment.get());

        return new ResponseEntity<>(previousCommentDto, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/comments", produces = "application/json")
    @Transactional
    @ResponseBody
    public ResponseEntity loadComments(@RequestParam Long foodId, @RequestParam int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Comment> comments = commentRepository.findAllByFoodId(foodId, pageable);
        if (!comments.isEmpty()) {
            List<CommentDto> commentsDto = new ArrayList<>();

            for (Comment comment : comments) {
                commentsDto.add(commentMapper.toDto(comment));
            }
            return new ResponseEntity<>(commentsDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/update/save")
    public ResponseEntity updateComment(@RequestBody CommentDto commentDto) {

        CommentDto updatedComment = commentService.update(commentDto);

        return new ResponseEntity(updatedComment, HttpStatus.OK);

    }

}
