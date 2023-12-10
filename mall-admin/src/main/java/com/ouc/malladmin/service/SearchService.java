package com.ouc.malladmin.service;

import com.ouc.malladmin.model.SearchModel;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.User;

import java.util.List;

public interface SearchService {
    List<SplitProduct> searchbywords(SearchModel searchModel);
    List<SplitProduct> searchbycategory(SearchModel searchModel);
    List<SplitProduct> searchcombine(SearchModel searchModel);
    List<User> searchuser(SearchModel searchModel);

}
