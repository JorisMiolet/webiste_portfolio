package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Laptop laptop;

    @ManyToOne
    private User user;

    public Image(Long id,
                 String articleNumber,
                 String ean,
                 String brand,
                 String description,
                 String processor,
                 String ram,
                 String storage,
                 String gpu,
                 String screenSizeInch,
                 String screenSizeCm,
                 String barcode,
                 String date,
                 String status) {
        this.id = id;
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

    public Image() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
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

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getScreenSizeCm() {
        return screenSizeCm;
    }

    public void setScreenSizeCm(String screenSizeCm) {
        this.screenSizeCm = screenSizeCm;
    }

    public String getScreenSizeInch() {
        return screenSizeInch;
    }

    public void setScreenSizeInch(String screenSizeInch) {
        this.screenSizeInch = screenSizeInch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
