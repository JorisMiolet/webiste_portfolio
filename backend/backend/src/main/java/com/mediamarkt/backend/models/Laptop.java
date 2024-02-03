package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JsonProperty("EAN")
    @Column(name = "EAN")
    private String ean;
    @JsonProperty("barcode")
    @Column(name = "BARCODE")
    private String laptopBarcode;
    @JsonProperty("brand")
    @Column(name = "BRAND")
    private String laptopBrand;
    @JsonProperty("description")
    @Column(name = "DESCRIPTION")
    private String laptopDescription;
    @OneToMany(mappedBy = "laptop", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Image> images;

    public Laptop(Long id, String ean, String laptopBarcode, String laptopBrand, String laptopDescription) {
        this.id = id;
        this.ean = ean;
        this.laptopBarcode = laptopBarcode;
        this.laptopBrand = laptopBrand;
        this.laptopDescription = laptopDescription;
    }

    public Laptop() {

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

    public String getLaptopBarcode() {
        return laptopBarcode;
    }

    public void setLaptopBarcode(String laptopBarcode) {
        this.laptopBarcode = laptopBarcode;
    }

    public String getLaptopBrand() {
        return laptopBrand;
    }

    public void setLaptopBrand(String laptopBrand) {
        this.laptopBrand = laptopBrand;
    }

    public String getLaptopDescription() {
        return laptopDescription;
    }

    public void setLaptopDescription(String laptopDescription) {
        this.laptopDescription = laptopDescription;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
