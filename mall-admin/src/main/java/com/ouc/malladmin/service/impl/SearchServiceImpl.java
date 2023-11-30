package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.SearchService;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Product> searchbywords(String key){
        List<Product> productList = new ArrayList<Product>();
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductNameLike('%' + key + '%');
        productList= productMapper.selectByExample(example);
        return productList;
    }
    @Override
    public List<Product> searchbycategory(int id){
        String[] categorys = {"手机", "电脑", "耳机"};
        List<Product> productList = new ArrayList<Product>();
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryEqualTo(categorys[id]);
        productList = productMapper.selectByExample(example);
        return productList;
    }
    @Override
    public List<Product> searchcombine(String key, int id){
        String[] categorys = {"手机", "电脑", "耳机"};
        List<Product> productList = new ArrayList<Product>();
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryEqualTo(categorys[id]);
        criteria.andProductNameLike('%' + key + '%');
        productList = productMapper.selectByExample(example);
        return productList;
    }
}
