package com.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;;
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
@Table(name = "program")
@Getter
@Setter
public class Program extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "price")
    private double price;

    @Column(name = "rating")
    private int rating;

    @Column(name = "type")
    private String type;

 //   @Column(name = "exit_date")
   // private Date exitDate;


    @Column(name = "image_url")
    private String image;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "program", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "program",fetch = FetchType.LAZY)
    //@JsonManagedReference
    private List<Description> descriptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_company_id")
    private Account company;


}
