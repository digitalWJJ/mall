package com.ouc.mallproduct.controller;

import com.ouc.mallmbg.model.Product;
import com.ouc.mallproduct.service.impl.ProductServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ouc.mallproduct.service.ProductService;
import com.ouc.mallcommon.Result;

@Controller
public class ProductController {
    @Resource
    ProductService productService;
    @GetMapping("/product/get/{productId}")
    public Result getProduct(@PathVariable("productId") int productId)
    {
        Product product=productService.getProduct(productId);
        if(product==null)
        {
           return Result.result(0,"获取具体商品信息失败",null);
        }
        else  return Result.success(product);
    }
    @PostMapping("/order/add/{productId}")
    public Result addProductIntoMyCart(@PathVariable("productId") int productId,String color,String configuration,int amount)
    {
        if(productService.addOrder(productId,color,configuration,amount))
        return Result.result(200,"添加购物车成功",null);
        else return  Result.result(0,"添加购物车失败",null);
    }
}
