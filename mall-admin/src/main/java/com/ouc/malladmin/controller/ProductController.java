package com.ouc.malladmin.controller;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.ProductService;
import com.ouc.malladmin.utils.SaveFileUtil;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.utils.OSS;
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
    ProductService productService;
    @Autowired
    SaveFileUtil saveFileUtil;

    @PostMapping("/addproduct")
    public Result addProduct(@RequestBody ProductModel productModel) {
        productService.addproduct(productModel);
        return Result.result(200, "添加成功", null);
    }
    @PostMapping("/update")
    public Result updateproduct(@RequestBody ProductModel productModel){
        productService.updateproduct(productModel);
        return Result.result(200, "更新成功", null);
    }
    @GetMapping("/viewproducts")
    public Result viewproducts(){
        List<Product> productList = new ArrayList<>();
        ProductExample productExample = new ProductExample();
        productList = productService.getproducts(productExample);
        if(productList==null) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
    @GetMapping("/viewproduct/{id}")
    public Result viewproduct(@PathVariable int id){
        Product product = new Product();
        product = productService.getproduct(id);
        if(product==null) {
            return Result.result(500,"获取商品失败",null);
        }
        else return Result.success(product);
    }
    @GetMapping("/getimage/{base}")
    public Result getimage(@PathVariable String base){
        String url = null;
        try {
            url = OSS.getProductImgUrl(saveFileUtil.savefile(base), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.result(200,"获取图片成功", url);
    }
    @DeleteMapping("/deleteproduct/{id}")
    public Result deleteproduct(@PathVariable int id){
        productService.deleteproduct(id);
        return Result.result(200, "删除成功", null);
    }

}
