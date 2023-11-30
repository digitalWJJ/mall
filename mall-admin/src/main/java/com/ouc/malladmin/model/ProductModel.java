package com.ouc.malladmin.model;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProductModel {
    private int id;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private String configuration;

    private String color;

    private String[] productImage;

    private String category;
    public int getId(){
        return id;
    }

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

    public String[] getProductImage() {
        return productImage;
    }

    public String getCategory() {
        return category;
    }



}
