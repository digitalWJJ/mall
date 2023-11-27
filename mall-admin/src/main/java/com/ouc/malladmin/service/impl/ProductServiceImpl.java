package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.ProductService;
import com.ouc.malladmin.utils.SaveFileUtils;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import org.springframework.util.FileCopyUtils;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    SaveFileUtils saveFileUtils;
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
        for(MultipartFile productImage: productModel.getProductImage()){
            if(productImage != null){
                UUID uuid = UUID.randomUUID();
                String uuiD = uuid.toString();
                String filename = productImage.getOriginalFilename();
                String suffix = uuiD + filename.substring(filename.lastIndexOf("."));
                File newfile = new File("localhost://admin/resources/images/" + suffix);
                try{
                    productImage.transferTo(newfile);
                }catch (IOException e){
                    e.printStackTrace();
                }
                product.setProductImage1(suffix);
            }

        }
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