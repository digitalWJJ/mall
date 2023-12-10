package com.ouc.login.controller;

import com.ouc.login.service.UserService;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
@RequestMapping("/api")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/info/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result getUserInfo(@PathVariable("id") int id)
    {
        User user=userService.getItem(id);
        if(user==null)return Result.result(500,"获取用户信息失败",null);
        return Result.result(200,"获取用户信息成功",user);
    }

    @RequestMapping(value = "/user/info/pwd/change/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateUserPwd(@PathVariable int id,  String password,  String verifyCode)
    {
        if(verifyCode.equals("22")&&userService.updatePassword(id,password)>0)
        {
            return Result.result(200,"密码修改成功",null);
        }
        return Result.result(500,"密码修改失败",null);
    }

    @RequestMapping(value = "user/info/change/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  Result updateUserInfo(@PathVariable int id, String userName, String gender, String stateMessage, String userImg)
    {
        if(userService.updateUserInfo(id,userName,gender,stateMessage,userImg)>0)
            return   Result.result(200,"用户信息修改成功",null);
        return   Result.result(500,"用户信息修改失败",null);
    }
}
