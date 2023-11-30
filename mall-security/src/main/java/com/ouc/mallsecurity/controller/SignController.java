package com.ouc.mallsecurity.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallmbg.model.User;
import com.ouc.mallsecurity.model.CodeModel;
import com.ouc.mallsecurity.model.PwdModel;
import com.ouc.mallsecurity.model.SignModel;
import com.ouc.mallsecurity.service.SignService;
import com.ouc.mallsecurity.utils.AesUtil;
import com.ouc.mallsecurity.utils.EmailValidatorUtil;
import com.ouc.mallsecurity.utils.RsaUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

@RestController
@RequestMapping(value = "/api")
public class SignController {
    @Autowired
    private SignService signService;

    @RequestMapping("/test")
    public Result test(){

//        try {
            /*System.out.println("私钥：" + RsaUtil.getPrivateKey());
            System.out.println("公钥：" + RsaUtil.getPublicKey());

            Scanner scanner = new Scanner(System.in);
            // 拿到前端的公钥
            String jsPublicKey = scanner.nextLine();
            // 获取java的 aesKey
            String javaAesKey = AesUtil.getKey();
            // 前端公钥加密自己的 aesKey
            String enJavaAesKey = new String( RsaUtil.encryptByPublicKey(javaAesKey.getBytes(), jsPublicKey) );
            // 用自己的 aesKey 加密 load
            String jsCiText = AesUtil.encrypt(str, javaAesKey);

            // 拿到前端被 java 公钥加密过的 aesKey
            String enJsAesKey = scanner.nextLine();
            // 拿到前端被 aesKey 加密过的 load
            String enLoad = scanner.nextLine();

            String jsAesKey = new String( RsaUtil.decryptByPrivateKey( Base64.decodeBase64(enJsAesKey.getBytes()), RsaUtil.getPrivateKey()) );
            String load = AesUtil.decrypt(enLoad, jsAesKey);
            JSONObject jsonObject = JSONUtil.parseObj(load);

            System.out.println("解密之后的内容：" + jsonObject);*/

//            Scanner scanner = new Scanner(System.in);
//            String encryptedAesKey = scanner.nextLine();
//            String load = scanner.nextLine();
//
//            String aesKey = new String( RsaUtil.decryptByPrivateKey( Base64.decodeBase64(encryptedAesKey.getBytes()), RsaUtil.getPrivateKey()) );
//            String data = AesUtil.decrypt(load, aesKey);
//            System.out.println(aesKey);
//            System.out.println(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return Result.success("this is test");
    }

    @RequestMapping("/testWrapper")
    public Result testWrapper(@RequestBody CodeModel codeModel ){
        System.out.println(codeModel);
        return Result.success();
    }

    @RequestMapping("/testRequest")
    public Result testRequest(@RequestBody CodeModel codeModel, HttpServletRequest request) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
        System.out.println( stringBuilder.toString());

        return Result.success();
    }

    @RequestMapping("/testNothing")
    public Result testNothing(){
        PwdModel pwdModel = new PwdModel();
        pwdModel.setEmail("2568840553@qq.com");
        pwdModel.setPwd("123456");
        return Result.success(pwdModel);
    }

    /**
    * 发送邮箱验证码
     *
     * @param email 接收验证码的邮箱
    * */
    @GetMapping("/getEmailCode")
    public Result getEmailCode(@RequestParam("email") String email){
        if( !EmailValidatorUtil.validateEmail(email) ) throw new ServiceException(402, "邮箱格式不正确");
        signService.sendEmailCode(email);
        return Result.success();
    }

    /**
     * 邮箱密码登录接口
     *
     * @param pwdModel 密码登陆对象
     * */
    @PostMapping("/signInByPwd")
    public Result signInByPwd(@RequestBody PwdModel pwdModel){

        RedisUtils.set("activeUserMap", new HashMap<Integer, User>()); // TODO 在项目启动之后配置 redis
        if( !EmailValidatorUtil.validateEmail(pwdModel.getEmail()) ) throw new ServiceException(402, "邮箱格式不正确");
        if( StrUtil.isBlank(pwdModel.getPwd()) ) throw new ServiceException(402, "密码不能为空");
        String token = signService.signInByPwd(pwdModel);
        return Result.success(token);
    }

    /**
     * 邮箱验证码登录接口
     *
     * @param codeModel 验证码登陆对象
     * */
    @PostMapping("/signInByCode")
    public Result signInByCode(@RequestBody CodeModel codeModel){
        if( !EmailValidatorUtil.validateEmail(codeModel.getEmail())) throw new ServiceException(402, "邮箱格式不正确");
        if( StrUtil.length(codeModel.getCode())!=6 ) throw new ServiceException(402, "无效验证码");
        String token = signService.signInByCode(codeModel);
        return Result.success(token);
    }

    /**
     * 注册接口
     * @param signModel 注册对象
     * */
    @PostMapping("/signup")
    public Result signUp(@RequestBody SignModel signModel){
        if( !EmailValidatorUtil.validateEmail(signModel.getEmail()) ) throw new ServiceException(402, "邮箱格式不正确");
        if( StrUtil.length(signModel.getCode())!=6 ) throw new ServiceException(402, "无效验证码");
        signService.signUp(signModel);
        return Result.result(200, "注册成功", null);
    }

    /**
     * 注册接口
     * @param signModel 注册对象
     * */
    @PostMapping("/admin/signup")
    public Result signUpAdmin(@RequestBody SignModel signModel){
        if( !EmailValidatorUtil.validateEmail(signModel.getEmail()) ) throw new ServiceException(402, "邮箱格式不正确");
        if( StrUtil.length(signModel.getCode())!=6 ) throw new ServiceException(402, "无效验证码");
        signService.signUp(signModel);
        return Result.result(200, "注册成功", null);
    }
}
