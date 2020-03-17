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


    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "program", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,mappedBy = "program", fetch = FetchType.LAZY)
    private Set<Order> order = new HashSet<>();


}