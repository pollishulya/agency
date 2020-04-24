package com.agency.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="reservation")
@Getter
@Setter
public class Reservation extends BaseEntity {

    @Column(name = "number_person")
    private int numberPerson;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    private Date date;

  //  @Column(name = "nameFood")
    //private String nameFood;

//    @Column(name = "nameLocation")
//    private String nameLocation;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

  //  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    //@JoinColumn(name = "location_id")
    //private Location location;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
