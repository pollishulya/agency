package com.agency.dto;

import com.agency.entity.Comment;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto extends BaseDto {
    private Long id;
    private String name;
  //  private String view;
    private String cuisine;
  //  private String cost;
    private String type;
    private String price;
  //  private String country;
  private String description;
    private float rating;
    private String image;
    private Set<Comment> comment = new HashSet<>();
    private Long companyId;
    private Set<ReservationDto> reservations = new HashSet<>();
}