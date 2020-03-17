//package com.agency.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "user")
//@Getter
//@Setter
//public class User extends BaseEntity {
//
//    @Column(name = "firstname")
//    private String firstname;
//
//    @Column(name = "middlename")
//    private String middlename;
//
//    @Column(name = "lastname")
//    private String lastname;
//
//    @Column(name = "phone")
//    private String phone;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "access")
//    private String access;
//
//    @Column(name = "login")
//    private String login;
//
//    @JsonIgnore
//    @Column(name = "password")
//    private String password;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "account")
//    private Client client;
//
//    @JsonIgnore
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval=true, mappedBy = "account", fetch = FetchType.LAZY)
//    private Set<Comment> comments = new HashSet<>();
//
//
//
//
//}
//
