package com.ouc.malladmin.service.impl;

import com.github.pagehelper.PageHelper;
import com.ouc.malladmin.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public boolean addproduct(SplitProduct splitProduct){
        Product product = new Product();
        product.setProductName(splitProduct.getProductName());
        product.setProductDescription(splitProduct.getProductDescription());
        product.setProductPrice(splitProduct.getProductPrice());
        String configurations = splitProduct.getConfiguration().get(0);
        for (int i = 0; i < splitProduct.getConfiguration().size(); i++){
            configurations = configurations + '|' + splitProduct.getConfiguration().get(i);
        }
        product.setConfiguration(configurations);
        String colors = splitProduct.getColor().get(0);
        for (int i = 0; i < splitProduct.getColor().size(); i++){
            colors = colors +  '|' + splitProduct.getColor().get(i);
        }
        product.setColor(colors);
        if(splitProduct.getProductImage() != null){
            if(!splitProduct.getProductImage().isEmpty()) product.setProductImage1(splitProduct.getProductImage().get(0));
            if(1 < splitProduct.getProductImage().size()) product.setProductImage2(splitProduct.getProductImage().get(1));
            if(2 < splitProduct.getProductImage().size()) product.setProductImage3(splitProduct.getProductImage().get(2));
            if(3 < splitProduct.getProductImage().size()) product.setProductImage4(splitProduct.getProductImage().get(3));
            if(4 < splitProduct.getProductImage().size()) product.setProductImage5(splitProduct.getProductImage().get(4));
        }
        product.setCategory(splitProduct.getCategory());
        return CacheTool.addProduct(product);
    }
    @Override
    public boolean updateproduct(SplitProduct splitProduct){
        Product product = new Product();
        product.setProductName(splitProduct.getProductName());
        product.setProductDescription(splitProduct.getProductDescription());
        product.setProductPrice(splitProduct.getProductPrice());
        String configurations = splitProduct.getConfiguration().get(0);
        for (int i = 0; i < splitProduct.getConfiguration().size(); i++){
            configurations = configurations + '|' + splitProduct.getConfiguration().get(i);
        }
        product.setConfiguration(configurations);
        String colors = splitProduct.getColor().get(0);
        for (int i = 0; i < splitProduct.getColor().size(); i++){
            colors = colors +  '|' + splitProduct.getColor().get(i);
        }
        product.setColor(colors);
        if(splitProduct.getProductImage() != null){
            if(!splitProduct.getProductImage().isEmpty()) product.setProductImage1(splitProduct.getProductImage().get(0));
            if(1 < splitProduct.getProductImage().size()) product.setProductImage2(splitProduct.getProductImage().get(1));
            if(2 < splitProduct.getProductImage().size()) product.setProductImage3(splitProduct.getProductImage().get(2));
            if(3 < splitProduct.getProductImage().size()) product.setProductImage4(splitProduct.getProductImage().get(3));
            if(4 < splitProduct.getProductImage().size()) product.setProductImage5(splitProduct.getProductImage().get(4));
        }
        product.setCategory(splitProduct.getCategory());
        return CacheTool.updateProduct(product);
    }
    @Override
    public List<SplitProduct> getproducts(int id){
        ProductExample productExample = new ProductExample();
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
        product = CacheTool.getProduct(id);
        SplitProduct splitProduct = null;
        try {
            splitProduct = TypeCasting.productToSplitProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return splitProduct;
    }
    @Override
    public boolean deleteproduct(Integer id){
        return CacheTool.deleteProduct(id);
    }
}