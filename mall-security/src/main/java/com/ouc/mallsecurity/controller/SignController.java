package com.ouc.mallsecurity.controller;

import com.ouc.mallcommon.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignController {

    @RequestMapping("/test")
    public Result test(){
        return Result.success();
    }


//    /*
//    * @description: 邮箱密码登录接口
//    * */
//    @PostMapping("/loginByPwd")
//    public Result loginByPwd(@RequestBody PwdModel pwdModel){
//
//        return Result.success(null);
//    }
//
//    /*
//     * @description: 邮箱验证码登录接口
//     * */
//    @PostMapping("/loginByCode")
//    public Result loginByCode(@RequestBody CodeModel codeModel){
//
//        return Result.success(null);
//    }
//
//    /*
//    * @description: 邮箱密码注册接口
//    * */
//    @PostMapping("/signupByPwd")
//    public Result signUpByPwd(@RequestBody PwdModel pwdModel){
//
//        return Result.success(null);
//    }
//
//    /*
//     * @description: 邮箱验证码注册接口
//     * */
//    @PostMapping("/signupByCode")
//    public Result signUp(@RequestBody CodeModel codeModel){
//
//        return Result.success(null);
//    }
}
