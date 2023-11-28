package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Laptop {

    @JsonProperty("EAN")
    public String ean;
    @JsonProperty("Barcode")
    public String barcode;
    @JsonProperty("Brand")
    public String brand;
    @JsonProperty("Description")
    public String description;

    public Laptop(String ean, String barcode, String brand, String description) {
        this.ean = ean;
        this.barcode = barcode;
        this.brand = brand;
        this.description = description;
    }
    public Laptop(){

    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getEan() {
        return ean;
    }
}
