package com.agency.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto extends BaseDto {

   /* private Long id;
    private int numberPerson;
    private Long foodId;
    private Long accountId;
    private String nameFood;
    private String username;
    private String phone;
    private String nameLocation;
    private Long locationId;
    private Date date;
    private Long companyId;
    private String status;*/
    private Long id;
    private int numberPerson;
    private Long foodId;
    private Long accountId;
    private String nameFood;
    private String username;
    private String phone;
    private Long locationId;
  //  private Date date;
   private Long companyId;
    /*private Long locationId;*/
    private Date date;
    private String status;
    // private Long programId;

}
