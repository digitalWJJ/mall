package com.ouc.login.controller;

import com.ouc.login.service.UserService;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.utils.TokenUtils;
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

    @RequestMapping(value = "/user/info/get/",method = RequestMethod.GET)
    @ResponseBody
    public Result getUserInfo()
    {
        int id= TokenUtils.getCurrentUser().getId();
        User user=userService.getItem(id);
        if(user==null)return Result.result(500,"获取用户信息失败",null);
        return Result.result(200,"获取用户信息成功",user);
    }

    @RequestMapping(value = "/user/info/pwd/change/",method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserPwd( String password,  String verifyCode)
    {
        int id=TokenUtils.getCurrentUser().getId();
        if(verifyCode.equals("22")&&userService.updatePassword(id,password)>0)
        {
            return Result.result(200,"密码修改成功",null);
        }
        return Result.result(500,"密码修改失败",null);
    }

    @RequestMapping(value = "user/info/change/",method = RequestMethod.POST)
    @ResponseBody
    public  Result updateUserInfo( String userName, String gender, String stateMessage, String userImg)
    {
        int id=TokenUtils.getCurrentUser().getId();
        if(userService.updateUserInfo(id,userName,gender,stateMessage,userImg)>0)
            return   Result.result(200,"用户信息修改成功",null);
        return   Result.result(500,"用户信息修改失败",null);
    }
}
