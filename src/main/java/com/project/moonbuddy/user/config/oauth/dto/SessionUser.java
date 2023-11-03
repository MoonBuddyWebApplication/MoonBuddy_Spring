package com.project.moonbuddy.user.config.oauth.dto;

import com.project.moonbuddy.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String picture;
    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
