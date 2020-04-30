package com.agency.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingLocationDto extends BaseDto {

        private Long id;
        private int numberPerson;
        private String nameLocation;
        private Long companyId;
        private Long locationId;
        private String username;
        private String phone;

        private Date date;
        private String status;
        private Long accountId;



}

