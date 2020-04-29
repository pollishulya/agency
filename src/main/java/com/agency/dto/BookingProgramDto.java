package com.agency.dto;

import lombok.Data;

import java.util.Date;

public class BookingProgramDto extends BaseDto {

        private Long id;
        private int numberPerson;
        private Long accountId;
        private String nameProgram;
        private String username;
        private String phone;
        private Long companyId;
        private Date date;
        private String status;
        private Long programId;
}

