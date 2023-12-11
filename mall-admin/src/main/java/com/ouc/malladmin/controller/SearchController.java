package com.ouc.malladmin.controller;
import com.github.pagehelper.PageHelper;
import com.ouc.malladmin.model.SearchModel;
import com.ouc.malladmin.service.SearchService;
import com.ouc.malladmin.service.impl.SearchServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.dto.SplitProduct;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/search")
public class SearchController {
    @Resource(type = com.ouc.malladmin.service.impl.SearchServiceImpl.class)
    SearchService searchService;
    @PostMapping("/searchbykey")
    public Result searchbywords(@RequestBody SearchModel searchModel){
        List<SplitProduct> productList = searchService.searchbywords(searchModel);
        if(productList.isEmpty()) return Result.result(402,"获取商品列表失败，商品列表为空",null);
        else return Result.success(productList);
    }
    @PostMapping("/searchbycategory")
    public Result searchbysearchbycategory(@RequestBody SearchModel searchModel){
        List<SplitProduct> productList = searchService.searchbycategory(searchModel);
        if(productList.isEmpty()) return Result.result(402,"获取商品列表失败，商品列表为空",null);
        else return Result.success(productList);
    }
    @PostMapping("/searchcombine")
    public Result searchbycombine(@RequestBody SearchModel searchModel){
        List<SplitProduct> productList = searchService.searchcombine(searchModel);
        if(productList.isEmpty()) return Result.result(402,"获取商品列表失败，商品列表为空",null);
        else return Result.success(productList);
    }

    @PostMapping("/searchusers")
    public Result searchusers(@RequestBody SearchModel searchModel){
        List<User> users = searchService.searchuser(searchModel);
        if(users.isEmpty()) return Result.result(402,"获取用户列表失败，用户列表为空",null);
        else return Result.success(users);
    }
}
