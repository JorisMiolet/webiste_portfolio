package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Book {

    private String name;
    private  long id;
    //@JsonIgnore
    private int publicatieJaar;
    private String author;

    public Book(String name, long id, int publicatieJaar, String author) {
        this.name = name;
        this.id = id;
        this.publicatieJaar = publicatieJaar;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPublicatieJaar() {
        return publicatieJaar;
    }

    public void setPublicatieJaar(int publicatieJaar) {
        this.publicatieJaar = publicatieJaar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
