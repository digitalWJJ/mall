package com.ouc.mallsecurity.controller;

import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.UserMapper;
//import com.ouc.mallsecurity.model.CodeModel;
//import com.ouc.mallsecurity.model.PwdModel;
import com.ouc.mallsecurity.model.CodeModel;
import com.ouc.mallsecurity.model.PwdModel;
import com.ouc.mallsecurity.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignController {
    @Autowired
    private SignService signService;

    @RequestMapping("/test")
    public Result test(){
        return Result.success();
    }

    /*
    * 测试邮箱验证码功能
    * */
    @GetMapping("/getEmailCode")
    public Result getEmailCode(@RequestParam("email") String email){
        System.out.println(email);
        signService.sendEmailCode(email);
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
