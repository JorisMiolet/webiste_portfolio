package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class user {
    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

    @JsonProperty("isAdmin")
    public boolean isAdmin;

    public user(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

}
