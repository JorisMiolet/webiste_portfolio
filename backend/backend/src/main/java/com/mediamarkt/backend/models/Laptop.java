package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "laptop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;

    public Laptop(Long id, String ean, String barcode, String brand, String description) {
        this.id = id;
        this.ean = ean;
        this.laptopBarcode = barcode;
        this.laptopBrand = brand;
        this.laptopDescription = description;
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

    public void associateImage(Image image) {
        this.images.add(image);
    }
}
