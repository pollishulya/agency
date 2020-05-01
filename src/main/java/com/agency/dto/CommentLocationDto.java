package com.agency.dto;

import com.agency.entity.Account;
import com.agency.entity.Food;
import com.agency.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLocationDto extends BaseDto {
    private Long id;
    private String message;
    private int rating;
   // private String date;
    private String time;
    private Account account;
    private Location location;

    private String username;
   private Long locationId;
    private Long accountId;
}