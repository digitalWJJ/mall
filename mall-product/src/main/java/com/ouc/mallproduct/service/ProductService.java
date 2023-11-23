package com.ouc.mallproduct.service;

import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Product;

public interface ProductService {
    Product getProduct(int id);

    boolean addOrder(int id, String color, String configuration, int amount);
}
