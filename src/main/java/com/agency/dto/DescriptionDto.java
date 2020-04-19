package com.agency.dto;//package com.agency.dto;


import lombok.Data;

@Data
public class DescriptionDto extends BaseDto {

    private Long id;
    private String description;
    private Integer dayNumber;
    //    private String tourName;
    private Long locationId;
    private Long foodId;

}
