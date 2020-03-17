package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.Order;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto extends BaseDto {
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private String date;
    private double price;
    private float rating;
    private Set<Comment> comment;
    private Order order;
}