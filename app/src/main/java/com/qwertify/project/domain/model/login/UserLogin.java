package com.qwertify.project.domain.model.login;

public class UserLogin {

    private String jwt;

    private String email;

    private String name;

    private String emailVerifiedAt;

    public UserLogin(String jwt, String email, String name, String emailVerifiedAt) {
        this.jwt = jwt;
        this.email = email;
        this.name = name;
        this.emailVerifiedAt = emailVerifiedAt;
    }
}
