package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.Order;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto extends BaseDto {
    private Long id;
    private String name;
    private String view;
    private String cuisine;
    private double price;
    private float rating;
    private Set<Comment> comment = new HashSet<>();;
    private Set<Order> order = new HashSet<>();;
    private String image;
    private List<FoodDescriptionDto> description = new ArrayList<>();
    private Long companyId;
}