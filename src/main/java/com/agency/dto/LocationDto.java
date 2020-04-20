package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.Order;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto extends BaseDto {
    private Long id;
    private String name;
    private String address;
    private int capacity;
 //   private String date;
    private double price;
    private int rating;
  //  private Set<Comment> comment;
   // private Order order;
  private Date exitDate;
    private String image;
    private Set<Comment> comment = new HashSet<>();;


    private List<DescriptionDto> description = new ArrayList<>();
    private Long companyId;
    private Set<ReservationDto> reservations = new HashSet<>();
}