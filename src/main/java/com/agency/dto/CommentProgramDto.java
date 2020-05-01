package com.agency.dto;

import com.agency.entity.Account;
import com.agency.entity.Location;
import com.agency.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentProgramDto extends BaseDto {
    private Long id;
    private String message;
    private int rating;
   // private String date;
    private String time;
    private Account account;
    private Program program;

    private String username;
   private Long programId;
    private Long accountId;
}