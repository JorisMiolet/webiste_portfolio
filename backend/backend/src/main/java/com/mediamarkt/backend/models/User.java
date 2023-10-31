package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

    @JsonProperty("uuid")
    public UUID uuid;

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

    @JsonProperty("admin")
    public boolean admin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.admin = isAdmin;
        this.uuid = UUID.randomUUID();
    }

}
