package com.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "location")
@Getter
@Setter
public class Location extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "date")
    private String date;

    @Column(name = "price")
    private double price;

    @Column(name = "rating")
    private float rating;

    @JsonIgnore
    @OneToOne(mappedBy = "location")
    private Order order;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "location", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();




}