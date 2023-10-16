package com.example.backend.data;

public class ConcreteUserData {
    String name;
    String password;
    boolean admin;

    public ConcreteUserData(String name, String password, boolean admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
}
