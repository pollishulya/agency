package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.Order;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto extends BaseDto {
    private Long id;
    private String name;
    private String duration;
    private String type;
    private double price;
    private float rating;
  //  private Date exitDate;
    private String image;
    private Set<Comment> comment = new HashSet<>();;
    private List<DescriptionDto> description = new ArrayList<>();
    private Long companyId;
    private Set<ReservationDto> reservations = new HashSet<>();
}