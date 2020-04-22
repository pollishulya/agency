package com.agency.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="food_company")
@Getter
@Setter
public class FoodCompany extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "company",fetch = FetchType.LAZY)
    private Set<Food> food = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "company",fetch = FetchType.LAZY)
    private Set<Location> location = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "program",fetch = FetchType.LAZY)
    private Set<Program> program = new HashSet<>();


}
