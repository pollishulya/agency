package com.agency.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="booking_program")
@Getter
@Setter
public class BookingProgram extends BaseEntity {

    @Column(name = "number_person")
    private int numberPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Account company;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;


    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;



}
