package com.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment_location")
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CommentLocation extends BaseEntity {

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private int rating;

//    @Column(name = "date")
  //  private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

   /* @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;*/

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;


}
