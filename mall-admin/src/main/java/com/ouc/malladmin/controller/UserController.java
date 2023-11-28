package com.ouc.malladmin.controller;

import com.ouc.malladmin.service.impl.UserServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userServiceImpl;
    @GetMapping("/admin/order/viewusers")
    public Result viewusers(){
        Result result = new Result();
        List<User> usersList = new ArrayList<User>();
        UserExample userExample = new UserExample();
        usersList = userServiceImpl.getusers(userExample);
        Result.success(usersList);
        return result;
    }

    @GetMapping("/admin/product/viewuser/{id}")
    public Result viewuser(@PathVariable int id){
        Result result = new Result();
        User user = new User();
        user = userServiceImpl.getuser(id);
        Result.success(user);
        return result;
    }
    @DeleteMapping("/admin/product/deleteuser/{id}")
    public Result deleteuser(@PathVariable int id){
        userServiceImpl.deleteuser(id);
        Result result = new Result();
        Result.success();
        return result;
    }
}
