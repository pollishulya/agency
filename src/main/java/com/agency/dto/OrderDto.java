package com.agency.dto;

import com.agency.entity.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends BaseDto {
    private Long id;
    private String date;
    private String time;
    private int people;
    private double price;
    private Location location;
    private Client client;
    private Food food;
    private Program program;
}