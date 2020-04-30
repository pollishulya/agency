package com.agency.dto;

import com.agency.entity.Account;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto extends BaseDto {
    private Long id;
    private String birthday;
    private int points;
    private int discount;
    private float bill;
    private Account account;
    private Set<Order> order;
}