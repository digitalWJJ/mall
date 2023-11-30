package com.ouc.malladmin.controller;
import com.ouc.malladmin.service.SearchService;
import com.ouc.malladmin.service.impl.SearchServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/search")
public class SearchController {
    @Autowired
    SearchService searchService;
    @GetMapping("/searchbykey/{key}")
    public Result searchbywords(@PathVariable String key){
        List<Product> productList = new ArrayList<>();
        productList = searchService.searchbywords(key);
        if(productList==null) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
    @GetMapping("/searchbywords/{id}")
    public Result searchbysearchbycategory(@PathVariable int id){
        Result result = new Result();
        List<Product> productList = new ArrayList<Product>();
        productList = searchService.searchbycategory(id);
        if(productList==null) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
    @GetMapping("/search/searchcombine/{key, id}")
    public Result searchbysearchbycategory(@PathVariable String key, @PathVariable int id){
        List<Product> productList = new ArrayList<Product>();
        productList = searchService.searchcombine(key, id);
        if(productList==null) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
}
