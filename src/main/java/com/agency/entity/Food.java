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
@Table(name = "food")
@Getter
@Setter
public class Food extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "view")
    private String view;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "price")
    private double price;

    @Column(name = "rating")
    private float rating;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "food", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,mappedBy = "food", fetch = FetchType.LAZY)
    private Set<Order> order = new HashSet<>();

  /*  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unicorn")
    public List<Droid> getDroids() {
        return droids;
    }*/

}
