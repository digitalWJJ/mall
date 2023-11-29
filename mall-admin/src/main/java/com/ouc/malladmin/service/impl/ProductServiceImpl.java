package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.ProductService;
import com.ouc.malladmin.utils.SaveFileUtil;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    SaveFileUtil saveFileUtils;
    @Override
    public void addproduct(ProductModel productModel){
        Product product = new Product();
        product.setProductName(productModel.getProductName());
        product.setProductDescription(productModel.getProductDescription());
        product.setProductPrice(productModel.getProductPrice());
        product.setConfiguration(productModel.getConfiguration());
        product.setColor(productModel.getColor());
        product.setProductImage1(saveFileUtils.savefile(productModel.getProductImage()[0]));
        product.setProductImage2(saveFileUtils.savefile(productModel.getProductImage()[1]));
        product.setProductImage3(saveFileUtils.savefile(productModel.getProductImage()[2]));
        product.setProductImage4(saveFileUtils.savefile(productModel.getProductImage()[3]));
        product.setProductImage5(saveFileUtils.savefile(productModel.getProductImage()[4]));
        product.setCategory(productModel.getCategory());
        productMapper.insert(product);
    }
    @Override
    public void updateproduct(ProductModel productModel){
        Product product = new Product();
        product.setProductName(productModel.getProductName());
        product.setProductDescription(productModel.getProductDescription());
        product.setProductPrice(productModel.getProductPrice());
        product.setConfiguration(productModel.getConfiguration());
        product.setColor(productModel.getColor());
        product.setProductImage1(saveFileUtils.savefile(productModel.getProductImage()[0]));
        product.setProductImage2(saveFileUtils.savefile(productModel.getProductImage()[1]));
        product.setProductImage3(saveFileUtils.savefile(productModel.getProductImage()[2]));
        product.setProductImage4(saveFileUtils.savefile(productModel.getProductImage()[3]));
        product.setProductImage5(saveFileUtils.savefile(productModel.getProductImage()[4]));
        product.setCategory(productModel.getCategory());
        productMapper.updateByPrimaryKey(product);
    }
    @Override
    public List<Product> getproducts(ProductExample productExample){
        List<Product> products = new ArrayList<Product>();
        products = productMapper.selectByExample(productExample);
        return products;
    }
    @Override
    public Product getproduct(Integer id){
        Product product = new Product();
        product = productMapper.selectByPrimaryKey(id);
        return product;
    }
    @Override
    public void deleteproduct(Integer id){
        productMapper.deleteByPrimaryKey(id);
    }
}