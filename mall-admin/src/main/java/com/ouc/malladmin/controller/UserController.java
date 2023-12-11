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
    UserService userService;

    @PostMapping("/adduser")
    public Result adduser(@RequestBody User user){
        if(userService.adduser(user)==0){
            return Result.result(500,"添加用户失败",null);
        }
        else return Result.result(200,"添加用户成功",null);
    }
    @PostMapping("/updateuser")
    public Result updateuser(@RequestBody User user){
        if(userService.updateuser(user) ==0){
            return Result.result(500,"更新用户信息失败",null);
        }
        else return Result.result(200,"更新用户信息成功",null);
    }
    @PostMapping("/viewusers")
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
