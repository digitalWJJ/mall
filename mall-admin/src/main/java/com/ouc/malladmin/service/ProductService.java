package com.ouc.malladmin.service;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addproduct(SplitProduct splitProduct);
    void updateproduct(SplitProduct splitProduct);
    List<SplitProduct> getproducts(ProductExample productExample);
    void deleteproduct(Integer id);
    SplitProduct getproduct(Integer id);

}
