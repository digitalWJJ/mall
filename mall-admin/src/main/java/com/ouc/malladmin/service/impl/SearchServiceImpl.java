package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.SearchService;
import com.ouc.malladmin.utils.TosplitproductUtil;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallcommon.tools.CacheTool;
import com.ouc.mallcommon.utils.TypeCasting;
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
    public List<SplitProduct> searchbywords(String key){
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductNameLike('%' + key + '%');
        List<Product> productList= productMapper.selectByExample(example);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(productList);
        return splitProducts;
    }
    @Override
    public List<SplitProduct> searchbycategory(int id){
        String[] categorys = {"手机", "电脑", "耳机"};
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryEqualTo(categorys[id]);
        List<Product> productList = productMapper.selectByExample(example);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(productList);
        return splitProducts;
    }
    @Override
    public List<SplitProduct> searchcombine(String key, int id){
        String[] categorys = {"手机", "电脑", "耳机"};
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryEqualTo(categorys[id]);
        criteria.andProductNameLike('%' + key + '%');
        List<Product> productList = productMapper.selectByExample(example);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(productList);
        return splitProducts;
    }
}
