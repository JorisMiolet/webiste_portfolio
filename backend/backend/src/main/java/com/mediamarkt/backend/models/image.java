package com.mediamarkt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class image {
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

    public image(String articleNumber,
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
