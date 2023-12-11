package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("EAN")
    private String ean;
    @JsonProperty("Barcode")
    private String barcode;
    @JsonProperty("Brand")
    private String brand;
    @JsonProperty("Description")
    private String description;

    public Laptop(Long id, String ean, String barcode, String brand, String description) {
        this.id = id;
        this.ean = ean;
        this.barcode = barcode;
        this.brand = brand;
        this.description = description;
    }
    public Laptop(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
