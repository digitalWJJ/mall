package com.ouc.malladmin.controller;

import com.ouc.malladmin.service.UserService;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/admin/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @GetMapping("/viewusers")
    public Result viewusers(@RequestBody PageParam pageParam){
        List<User> usersList = userService.getusers(pageParam);
        if(usersList.isEmpty()) {
            return Result.result(500,"获取用户列表失败",null);
        }
        else return Result.success(usersList);
    }

    @GetMapping("/viewuser/{id}")
    public Result viewuser(@PathVariable int id){
        User user = userService.getuser(id);
        if(user==null) {
            return Result.result(500,"获取用户信息失败",null);
        }
        else return Result.success(user);
    }
    @DeleteMapping("/deleteuser/{id}")
    public Result deleteuser(@PathVariable int id){
        userService.deleteuser(id);
        return Result.result(200, "删除成功", null);
    }
}
