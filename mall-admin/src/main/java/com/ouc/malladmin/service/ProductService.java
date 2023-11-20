package com.ouc.malladmin.service;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addproduct(ProductModel productModel);
    void updateproduct(ProductModel productModel);
    List<Product> getproducts(ProductExample productExample);
    void deleteproduct(Integer id);
    Product getproduct(Integer id);

}
