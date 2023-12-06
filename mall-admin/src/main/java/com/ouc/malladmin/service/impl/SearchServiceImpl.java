package com.ouc.malladmin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ouc.malladmin.model.SearchModel;
import com.ouc.malladmin.service.SearchService;
import com.ouc.malladmin.utils.TosplitproductUtil;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.PageParam;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<SplitProduct> searchbywords(SearchModel searchModel){
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductNameLike('%' + searchModel.getKey() + '%');
        example.setLimitStart((searchModel.getPageIndex()-1) * searchModel.getPageSize());
        example.setLimitSize(searchModel.getPageSize());
        List<Product> productList= productMapper.selectByExample(example);
        System.out.println(productList);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(productList);
        return splitProducts;
    }
    @Override
    public List<SplitProduct> searchbycategory(SearchModel searchModel){
        String[] categorys = {"手机", "电脑", "耳机"};
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryEqualTo(categorys[searchModel.getId()]);
        example.setLimitStart((searchModel.getPageIndex()-1) * searchModel.getPageSize());
        example.setLimitSize(searchModel.getPageSize());
        List<Product> productList = productMapper.selectByExample(example);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(productList);
        return splitProducts;
    }
    @Override
    public List<SplitProduct> searchcombine(SearchModel searchModel){
        String[] categorys = {"手机", "电脑", "耳机"};
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryEqualTo(categorys[searchModel.getId()]);
        criteria.andProductNameLike('%' + searchModel.getKey() + '%');
        example.setLimitStart((searchModel.getPageIndex()-1) * searchModel.getPageSize());
        example.setLimitSize(searchModel.getPageSize());
        List<Product> productList = productMapper.selectByExample(example);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(productList);
        return splitProducts;
    }
}
