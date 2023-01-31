package com.app.magiclamp.model;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.entity.UserRole;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    @NotBlank
    @Pattern(regexp = "^[0-9a-z]{3,11}$")
    private String username;
    @NotBlank
    private String password;

    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{4})([0-9]{4})$")
    private String phone;

    @NotBlank
    private String postnum;
    @NotBlank
    private String address1;
    @NotBlank
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
