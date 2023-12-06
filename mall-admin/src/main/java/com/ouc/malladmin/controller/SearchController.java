package com.ouc.malladmin.controller;
import com.github.pagehelper.PageHelper;
import com.ouc.malladmin.model.SearchModel;
import com.ouc.malladmin.service.SearchService;
import com.ouc.malladmin.service.impl.SearchServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/search")
public class SearchController {
    @Autowired
    SearchService searchService;
    @GetMapping("/searchbykey")
    public Result searchbywords(@RequestBody SearchModel searchModel){
        List<SplitProduct> productList = searchService.searchbywords(searchModel);
        if(productList.isEmpty()) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
    @GetMapping("/searchbycategory")
    public Result searchbysearchbycategory(@RequestBody SearchModel searchModel){
        List<SplitProduct> productList = searchService.searchbycategory(searchModel);
        if(productList.isEmpty()) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
    @GetMapping("/searchcombine")
    public Result searchbycombine(@RequestBody SearchModel searchModel){
        List<SplitProduct> productList = searchService.searchcombine(searchModel);
        if(productList.isEmpty()) {
            return Result.result(500,"获取商品列表失败",null);
        }
        else return Result.success(productList);
    }
}
