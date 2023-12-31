package com.ouc.malladmin.service;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.PageParam;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    boolean addproduct(SplitProduct splitProduct);
    boolean updateproduct(SplitProduct splitProduct);
    List<SplitProduct> getproducts(PageParam pageParam);
    boolean deleteproduct(Integer id);
    SplitProduct getproduct(Integer id);

}
