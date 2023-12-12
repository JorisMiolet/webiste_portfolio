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
    private String laptopBarcode;
    @JsonProperty("Brand")
    private String laptopBrand;
    @JsonProperty("Description")
    private String laptopDescription;

    public Laptop(Long id, String ean, String barcode, String brand, String description) {
        this.id = id;
        this.ean = ean;
        this.laptopBarcode = barcode;
        this.laptopBrand = brand;
        this.laptopDescription = description;
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
        return laptopBarcode;
    }

    public void setBarcode(String barcode) {
        this.laptopBarcode = barcode;
    }

    public String getBrand() {
        return laptopBrand;
    }

    public void setBrand(String brand) {
        this.laptopBrand = brand;
    }

    public String getDescription() {
        return laptopDescription;
    }

    public void setDescription(String description) {
        this.laptopDescription = description;
    }
}
