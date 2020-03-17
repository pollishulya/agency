package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.Order;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto extends BaseDto {
    private Long id;
    private String name;
    private String duration;
    private double price;
    private float rating;
    private Set<Comment> comment;
    private Set<Order> order;
}