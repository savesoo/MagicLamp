package com.app.magiclamp.model;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.entity.UserRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private String username;
    private String password;

    private String name;
    private String phone;

    private String postnum;
    private String address1;
    private String address2;

    private Set<UserRole> userRoleSet;

    public User toUser(){

        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .phone(phone)
                .postnum(postnum)
                .address1(address1)
                .address2(address2)
                .role(userRoleSet)
                .build();
    }
}
