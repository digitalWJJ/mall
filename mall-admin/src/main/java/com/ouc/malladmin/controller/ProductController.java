package com.ouc.malladmin.controller;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.impl.ProductServiceImpl;
import com.ouc.malladmin.utils.ToMultipartFileUtils;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    ToMultipartFileUtils toMultipartFileUtils;

    @PostMapping("/admin/product/addproduct")
    public Result addProduct(@RequestBody ProductModel productModel) {
        Result result = new Result();
        productServiceImpl.addproduct(productModel);
        Result.success();
        return result;
    }
    @PutMapping("/admin/product/updateproduct/id")
    public Result updateproduct(@RequestBody ProductModel productModel){
        Result result = new Result();
        productServiceImpl.updateproduct(productModel);
        Result.success();
        return result;
    }

    @GetMapping("/admin/product/viewproducts/")
    public Result viewproducts(){
        Result result = new Result();
        List<Product> productList = new ArrayList<Product>();
        ProductExample productExample = new ProductExample();
        productList = productServiceImpl.getproducts(productExample);
        Result.success(productList);
        http://101./admin/product/images/123.png
        return result;
    }
    @GetMapping("/admin/product/viewproduct/{id}")
    public Result viewproduct(@PathVariable int id){
        Result result = new Result();
        Product product = new Product();
        product = productServiceImpl.getproduct(id);
        Result.success(product);
        return result;
    }
    @GetMapping("/admin/product/images/{name}")
    public Result getimage(@PathVariable String name){
        Result result = new Result();
        MultipartFile multipartFile;
        multipartFile = toMultipartFileUtils.tomutipartfile(name);
        Result.success(multipartFile);
        return result;
    }
    @DeleteMapping("/admin/product/deleteproduct/{id}")
    public Result deleteproduct(@PathVariable int id){
        productServiceImpl.deleteproduct(id);
        Result result = new Result();
        Result.success();
        return result;
    }



}
