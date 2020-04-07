package com.agency.dto;

import com.agency.entity.Comment;
import com.agency.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto extends BaseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String access;
    private String login;
    private String password;
    private Set<Comment> comment;



    private String role;
    private Set<Role> roleSet;
}
