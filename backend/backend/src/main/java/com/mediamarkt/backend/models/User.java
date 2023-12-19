package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "employee")
public class User {

    @Id
    @JsonProperty("uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("locatie")
    private String locatie;

    @JsonProperty("admin")
    private boolean admin;

    public User(String username, String password, boolean isAdmin, String locatie, String email) {
        // create user
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.admin = isAdmin;
        this.locatie = locatie;
        this.email = email;
    }

    public User() {
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID id) {
        this.uuid = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }
}
