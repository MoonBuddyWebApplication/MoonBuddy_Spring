package com.project.moonbuddy.auth.model;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User {
    private final Long id;
    public UserPrincipal(com.project.moonbuddy.user.model.User user) {
        super(user.getUserId(), user.getPw(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.id = user.getId();
    }

    public Long getId(){
        return id;
    }
}
