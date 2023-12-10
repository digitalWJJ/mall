package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.ProductService;
import com.ouc.malladmin.utils.ToproductUtil;
import com.ouc.malladmin.utils.TosplitproductUtil;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallcommon.tools.CacheTool;
import com.ouc.mallcommon.utils.TypeCasting;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.PageParam;
import com.ouc.mallmbg.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public boolean addproduct(SplitProduct splitProduct){
        Product product = ToproductUtil.toproduct(splitProduct);
        return CacheTool.addProduct(product);
    }
    @Override
    public boolean updateproduct(SplitProduct splitProduct){
        Product product = ToproductUtil.toproduct(splitProduct);
        return CacheTool.updateProduct(product);
    }
    @Override
    public List<SplitProduct> getproducts(PageParam pageParam){
        List<Product> products = CacheTool.getProducts(pageParam);
        List<SplitProduct> splitProducts = TosplitproductUtil.getSplitproducts(products);
        return splitProducts;
    }
    @Override
    public SplitProduct getproduct(Integer id){
        Product product = CacheTool.getProduct(id);
        SplitProduct splitProduct = TosplitproductUtil.getsplitproduct(product);
        return splitProduct;
    }
    @Override
    public boolean deleteproduct(Integer id){
        return CacheTool.deleteProduct(id);
    }
}