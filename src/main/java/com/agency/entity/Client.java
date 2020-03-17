package com.agency.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Client extends BaseEntity{

@Column(name = "birthday")
private String birthday;

@Column(name = "points")
private int points;

@Column(name = "discount")
private int discount;

@Column(name = "bill")
private float bill;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Account account;
   /* @JsonIgnore
    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "account_id")
    private Account account;*/

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> order = new HashSet<>();


//@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//@JoinColumn(name = "food_idfood")
//@JsonIgnore
//private Food food;
//
//@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//@JoinColumn(name = "program_idprogram")
//@JsonIgnore
//private Program program;
//
//@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//@JoinColumn(name = "location_idlocation")
//@JsonIgnore
//private Location location;

}