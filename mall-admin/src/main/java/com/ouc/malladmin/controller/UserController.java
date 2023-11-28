package com.ouc.malladmin.controller;

import com.ouc.malladmin.service.impl.UserServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/admin/order")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userServiceImpl;
    @GetMapping("/viewusers")
    public Result viewusers(){
        List<User> usersList = new ArrayList<User>();
        UserExample userExample = new UserExample();
        usersList = userServiceImpl.getusers(userExample);
        if(usersList==null) {
            return Result.result(500,"获取用户列表失败",null);
        }
        else return Result.success(usersList);
    }

    @GetMapping("/viewuser/{id}")
    public Result viewuser(@PathVariable int id){
        User user = new User();
        user = userServiceImpl.getuser(id);
        if(user==null) {
            return Result.result(500,"获取用户信息失败",null);
        }
        else return Result.success(user);
    }
    @DeleteMapping("/admin/product/deleteuser/{id}")
    public Result deleteuser(@PathVariable int id){
        userServiceImpl.deleteuser(id);
        Result result = new Result();
        return Result.result(200, "删除成功", null);
    }
}
