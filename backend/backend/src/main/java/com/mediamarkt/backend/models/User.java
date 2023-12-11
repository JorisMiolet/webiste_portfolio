package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "employee")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("admin")
    private boolean admin;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public User(String username, String password, boolean isAdmin) {
        // create user
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.admin = isAdmin;
    }

    public User(){}

    public void setId(UUID id) {
        this.uuid = id;
    }

    public UUID getId() {
        return uuid;
    }
}
