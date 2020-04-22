package com.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "food")
@Getter
@Setter
public class Food extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "view")
    private String view;

    @Column(name = "exit_date")
    private Date exitDate;

    @Column(name = "number_days")
    private String numberDays;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "price")
    private double price;

    @Column(name = "cost")
    private String cost;

    @Column(name = "type")
    private String type;

    @Column(name = "rating")
    private float rating;

    @Column(name = "image_url")
    private String image;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "food", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

  /*  @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,mappedBy = "food", fetch = FetchType.LAZY)
    private Set<Order> order = new HashSet<>();*/

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "food",fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

  /*  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unicorn")
    public List<Droid> getDroids() {
        return droids;
    }*/
  @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "food",fetch = FetchType.LAZY)
 // @JsonManagedReference
  private List<FoodDescription> foodDescriptions = new ArrayList<>();

//    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "location",fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<FoodDescription> locationDescriptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_company_id")
    private Account company;
}
