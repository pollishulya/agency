package com.agency.dto;

import com.agency.entity.Account;
import com.agency.entity.Location;
import com.agency.entity.Food;
import com.agency.entity.Program;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto extends BaseDto {
    private Long id;
    private String message;
    private int rating;
    private String date;
    private String time;
    private Account account;
    private Food food;
    private Location location;
    private Program program;
}