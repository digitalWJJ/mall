package com.ouc.malladmin.controller;
import com.ouc.malladmin.service.SearchService;
import com.ouc.malladmin.service.impl.SearchServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class SearchController {
    @Autowired
    SearchServiceImpl searchServiceImpl;
    @GetMapping("/search/searchbywords/{key}")
    public Result searchbywords(@PathVariable String key){
        Result result = new Result();
        List<Product> productList = new ArrayList<Product>();
        productList = searchServiceImpl.searchbywords(key);
        Result.success(productList);
        return result;
    }
    @GetMapping("/search/searchbywords/{id}")
    public Result searchbysearchbycategory(@PathVariable int id){
        Result result = new Result();
        List<Product> productList = new ArrayList<Product>();
        productList = searchServiceImpl.searchbycategory(id);
        Result.success(productList);
        return result;
    }
    @GetMapping("/search/searchcombine/{key,id}")
    public Result searchbysearchbycategory(@PathVariable String key, @PathVariable int id){
        Result result = new Result();
        List<Product> productList = new ArrayList<Product>();
        productList = searchServiceImpl.searchcombine(key, id);
        Result.success(productList);
        return result;
    }
}
