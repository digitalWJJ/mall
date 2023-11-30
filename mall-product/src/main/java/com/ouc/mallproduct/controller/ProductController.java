package com.ouc.mallproduct.controller;

import com.aliyuncs.exceptions.ClientException;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallcommon.utils.TypeCasting;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ouc.mallproduct.service.ProductService;
import com.ouc.mallcommon.Result;

@Controller
public class ProductController {
    @Resource
    ProductService productService;
    @RequestMapping(value = "/product/get/{productId}",method = RequestMethod.GET)
    @ResponseBody
    public Result getProduct(@PathVariable("productId") int productId) throws ClientException {
        Product product=productService.getItem(productId);
        if(product==null)
        {
           return Result.result(500,"获取具体商品信息失败",null);
        }
        return Result.success(TypeCasting.productToSplitProduct(product));
    }
}
