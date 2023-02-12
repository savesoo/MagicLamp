package com.app.magiclamp.model.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

@Getter
public class AuthUserDTO extends User {
    private int userindex;
    private String name;

    public AuthUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       int userindex, String name) {
        super(username, password, authorities);
        this.userindex = userindex;
        this.name = name;
    }
}