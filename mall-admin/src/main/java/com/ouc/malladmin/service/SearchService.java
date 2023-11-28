package com.ouc.malladmin.service;

import com.ouc.mallmbg.model.Product;

import java.util.List;

public interface SearchService {
    List<Product> searchbywords(String key);
    List<Product> searchbycategory(int id);
    List<Product> searchcombine(String key, int id);
}
