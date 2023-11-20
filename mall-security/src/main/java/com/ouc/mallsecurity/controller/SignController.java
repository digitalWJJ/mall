package com.ouc.mallsecurity.controller;

import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallsecurity.model.CodeModel;
import com.ouc.mallsecurity.model.PwdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    @RequestMapping("/test")
    public Result test(){
        return Result.success();
    }

    /*
    * @description: 邮箱密码登录接口
    * */
    @PostMapping("/loginByPwd")
    public Result loginByPwd(@RequestBody PwdModel pwdModel){

        return Result.success(null);
    }

    /*
     * @description: 邮箱验证码登录接口
     * */
    @PostMapping("/loginByCode")
    public Result loginByCode(@RequestBody CodeModel codeModel){

        return Result.success(null);
    }

    /*
    * @description: 邮箱密码注册接口
    * */
    @PostMapping("/signupByPwd")
    public Result signUpByPwd(@RequestBody PwdModel pwdModel){

        return Result.success(null);
    }

    /*
     * @description: 邮箱验证码注册接口
     * */
    @PostMapping("/signupByCode")
    public Result signUp(@RequestBody CodeModel codeModel){

        return Result.success(null);
    }
}
