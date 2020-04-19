package com.agency.dto;

import lombok.Data;

@Data
public class FoodDescriptionDto extends BaseDto {

    private Long id;
    private String description;
    private Integer dayNumber;
//    private String tourName;
    private Long foodId;
    private  Long locationId;
}
