package com.ouc.malladmin.controller;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.impl.ProductServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/admin/product")
public class ProductController {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductServiceImpl productServiceImpl;

    @PostMapping("/addproduct")
    public Result addProduct(@RequestBody ProductModel productModel) {
        productServiceImpl.addproduct(productModel);
        return Result.success();
    }
    @PutMapping("/updateproduct/id")
    public Result updateproduct(@RequestBody ProductModel productModel){
        productServiceImpl.updateproduct(productModel);
        return Result.result(200, "更新成功", null);
    }
    @GetMapping("/viewproducts/")
    public Result viewproducts(){
        List<Product> productList = new ArrayList<Product>();
        ProductExample productExample = new ProductExample();
        productList = productServiceImpl.getproducts(productExample);
        if(productList==null) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
    @GetMapping("/viewproduct/{id}")
    public Result viewproduct(@PathVariable int id){
        Product product = new Product();
        product = productServiceImpl.getproduct(id);
        if(product==null) {
            return Result.result(500,"获取商品失败",null);
        }
        else return Result.success(product);
    }
    @GetMapping("/images/{name}")
    public Result getimage(@PathVariable String name){
        return Result.success();
    }
    @DeleteMapping("/deleteproduct/{id}")
    public Result deleteproduct(@PathVariable int id){
        productServiceImpl.deleteproduct(id);
        return Result.result(200, "删除成功", null);
    }

}
