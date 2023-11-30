package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.ProductService;
import com.ouc.malladmin.utils.SaveFileUtil;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallcommon.utils.TypeCasting;
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
        if(productModel.getProductImage() != null){
            if(0 < productModel.getProductImage().length) product.setProductImage1(saveFileUtils.savefile(productModel.getProductImage()[0]));
            if(1 < productModel.getProductImage().length) product.setProductImage2(saveFileUtils.savefile(productModel.getProductImage()[1]));
            if(2 < productModel.getProductImage().length) product.setProductImage3(saveFileUtils.savefile(productModel.getProductImage()[2]));
            if(3 < productModel.getProductImage().length) product.setProductImage4(saveFileUtils.savefile(productModel.getProductImage()[3]));
            if(4 < productModel.getProductImage().length) product.setProductImage5(saveFileUtils.savefile(productModel.getProductImage()[4]));
        }
        product.setCategory(productModel.getCategory());
        productMapper.insert(product);
    }
    @Override
    public void updateproduct(ProductModel productModel){
        Product product = new Product();
        product.setId(productModel.getId());
        product.setProductName(productModel.getProductName());
        product.setProductDescription(productModel.getProductDescription());
        product.setProductPrice(productModel.getProductPrice());
        product.setConfiguration(productModel.getConfiguration());
        product.setColor(productModel.getColor());
        if(productModel.getProductImage() != null){
            if(0 < productModel.getProductImage().length) product.setProductImage1(saveFileUtils.savefile(productModel.getProductImage()[0]));
            if(1 < productModel.getProductImage().length) product.setProductImage2(saveFileUtils.savefile(productModel.getProductImage()[1]));
            if(2 < productModel.getProductImage().length) product.setProductImage3(saveFileUtils.savefile(productModel.getProductImage()[2]));
            if(3 < productModel.getProductImage().length) product.setProductImage4(saveFileUtils.savefile(productModel.getProductImage()[3]));
            if(4 < productModel.getProductImage().length) product.setProductImage5(saveFileUtils.savefile(productModel.getProductImage()[4]));
        }
        productMapper.updateByPrimaryKeySelective(product);
    }
    @Override
    public List<SplitProduct> getproducts(ProductExample productExample){
        List<Product> products = new ArrayList<>();
        products = productMapper.selectByExample(productExample);
        List<SplitProduct> splitProducts = new ArrayList<>();
        try {
            for(int i = 0; i < products.size(); i++){
                splitProducts.add(TypeCasting.productToSplitProduct(products.get(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return splitProducts;
    }
    @Override
    public SplitProduct getproduct(Integer id){
        Product product = new Product();
        product = productMapper.selectByPrimaryKey(id);
        SplitProduct splitProduct = null;
        try {
            splitProduct = TypeCasting.productToSplitProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splitProduct;
    }
    @Override
    public void deleteproduct(Integer id){
        productMapper.deleteByPrimaryKey(id);
    }
}