package com.ouc.malladmin.model;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProductModel {

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private String configuration;

    private String color;

    private MultipartFile[] productImage;

    private String category;

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getConfiguration() {
        return configuration;
    }

    public String getColor() {
        return color;
    }

    public MultipartFile[] getProductImage() {
        return productImage;
    }

    public String getCategory() {
        return category;
    }



}
