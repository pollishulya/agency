//package com.agency.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "food_description")
//@Getter
//@Setter
//public class LocationDescription extends BaseEntity {
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "day_number")
//    private int dayNumber;
//
//    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_location")
//    @JsonBackReference
//    private Location location;
//
//}
