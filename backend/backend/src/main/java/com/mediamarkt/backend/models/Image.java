package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("Article NR")
    private String articleNumber;

    @JsonProperty("EAN")
    private String ean;
    @JsonProperty("Brand")
    private String brand;
    @JsonProperty("Description / Model type")
    private String description;
    @JsonProperty("PROCESSOR")
    private String processor;
    @JsonProperty("RAM")
    private String ram;
    @JsonProperty("STORAGE")
    private String storage;
    @JsonProperty("GPU")
    private String gpu;
    @JsonProperty("SCREENSIZE (inch)")
    private String screenSizeInch;
    @JsonProperty("SCREENSIZE (cm)")
    private String screenSizeCm;
    @JsonProperty("BARCODE")
    private String barcode;
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    public Image(String articleNumber,
                 String ean, String brand,
                 String description, String processor,
                 String ram, String storage,
                 String gpu, String screenSizeInch,
                 String screenSizeCm, String barcode,String date,String status) {
        this.articleNumber = articleNumber;
        this.ean = ean;
        this.brand = brand;
        this.description = description;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.gpu = gpu;
        this.screenSizeInch = screenSizeInch;
        this.screenSizeCm = screenSizeCm;
        this.barcode = barcode;
        this.date = date;
        this.status = status;
    }

    public Image(){}

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getEan() {
        return ean;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getProcessor() {
        return processor;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setScreenSizeCm(String screenSizeCm) {
        this.screenSizeCm = screenSizeCm;
    }

    public String getScreenSizeCm() {
        return screenSizeCm;
    }

    public void setScreenSizeInch(String screenSizeInch) {
        this.screenSizeInch = screenSizeInch;
    }

    public String getScreenSizeInch() {
        return screenSizeInch;
    }

    public Long getId() {
        return id;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
