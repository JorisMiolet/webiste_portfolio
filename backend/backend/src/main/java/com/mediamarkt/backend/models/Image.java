package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty("Article NR")
    public String articleNumber;
    @JsonProperty("EAN")
    public String ean;
    @JsonProperty("Brand")
    public String brand;
    @JsonProperty("Description / Model type")
    public String description;
    @JsonProperty("PROCESSOR")
    public String processor;
    @JsonProperty("RAM")
    public String ram;
    @JsonProperty("STORAGE")
    public String storage;
    @JsonProperty("GPU")
    public String gpu;
    @JsonProperty("SCREENSIZE (inch)")
    public String screenSizeInch;
    @JsonProperty("SCREENSIZE (cm)")
    public String screenSizeCm;

    @JsonProperty("BARCODE")
    public String barcode;

    public Image(String articleNumber,
                 String ean, String brand,
                 String description, String processor,
                 String ram, String storage,
                 String gpu, String screenSizeInch,
                 String screenSizeCm, String barcode) {
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
    }

    public Image(){}
}
