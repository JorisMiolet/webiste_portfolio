package com.example.backend.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConcreteLaptopData {
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

    public ConcreteLaptopData(String articleNumber,
                              String ean, String brand,
                              String description, String processor,
                              String ram, String storage,
                              String gpu, String screenSizeInch,
                              String screenSizeCm) {
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
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public String getEan() {
        return ean;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGpu() {
        return gpu;
    }

    public String getScreenSizeInch() {
        return screenSizeInch;
    }

    public String getScreenSizeCm() {
        return screenSizeCm;
    }
}
