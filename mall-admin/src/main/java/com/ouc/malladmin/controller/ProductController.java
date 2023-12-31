package com.ouc.malladmin.controller;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.ProductService;
import com.ouc.malladmin.utils.SaveFileUtil;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.PageParam;
import com.ouc.mallmbg.model.ProductExample;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/admin/product")
public class ProductController {
    @Resource(type = com.ouc.malladmin.service.impl.ProductServiceImpl.class)
    ProductService productService;

    @PostMapping("/addproduct")
    public Result addProduct(@RequestBody SplitProduct splitProduct) {
        if(productService.addproduct(splitProduct)) return Result.result(200, "添加成功", null);
        else return Result.result(500, "添加失败，请重新操作", null);
    }
    @PostMapping("/updateproduct")
    public Result updateproduct(@RequestBody SplitProduct splitProduct){
        if(productService.updateproduct(splitProduct)) return Result.result(200, "更新成功", null);
        else return Result.result(402, "更新失败，请重新操作", null);
    }
    @PostMapping("/viewproducts")
    public Result viewproducts(@RequestBody PageParam pageParam){
        List<SplitProduct> splitProducts = new ArrayList<>();
        splitProducts = productService.getproducts(pageParam);
        if(splitProducts.isEmpty()) return Result.result(402,"获取商品列表失败,商品列表为空",null);
        else return Result.success(splitProducts);
    }
    @GetMapping("/viewproduct/{id}")
    public Result viewproduct(@PathVariable int id){
        SplitProduct splitProduct = productService.getproduct(id);
        if(splitProduct==null) return Result.result(500,"获取商品失败，不存在该商品",null);
        else return Result.success(splitProduct);
    }
    @DeleteMapping("/deleteproduct/{id}")
    public Result deleteproduct(@PathVariable int id){
        if(productService.deleteproduct(id)) return Result.result(200, "删除成功", null);
        else return Result.result(402, "删除失败，请重新操作", null);
    }

}
