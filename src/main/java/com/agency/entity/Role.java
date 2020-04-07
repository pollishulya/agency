package com.agency.entity;

import com.agency.enums.Roles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_to_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accountSet = new HashSet<>();

}
