package com.agency.entity;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseEntity(Long id) {
        this.id=id;
    }

    public BaseEntity() {
    }
}
