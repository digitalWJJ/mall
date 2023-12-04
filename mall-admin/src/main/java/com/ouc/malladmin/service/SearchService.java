package com.ouc.malladmin.service;

import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;

import java.util.List;

public interface SearchService {
    List<SplitProduct> searchbywords(String key);
    List<SplitProduct> searchbycategory(int id);
    List<SplitProduct> searchcombine(String key, int id);
}
