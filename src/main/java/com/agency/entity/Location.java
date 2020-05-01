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

  //  @Column(name = "date")
    //private String date;

    @Column(name = "price")
    private String price;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
   private String description;

    @Column(name = "rating")
    private int rating;

    //@Column(name = "exit_date")
    //private Date exitDate;

    @Column(name = "image_url")
    private String image;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "location", fetch = FetchType.LAZY)
    private Set<CommentLocation> comments = new HashSet<>();

  @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "location",fetch = FetchType.LAZY)
    //@JsonManagedReference
    private List<Description> descriptions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "location",fetch = FetchType.LAZY)
    private Set<BookingLocation> reservation = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_company_id")
    private Account company;


}