package com.ouc.mallsecurity.service;

import com.ouc.mallmbg.model.User;
import com.ouc.mallsecurity.model.CodeModel;
import com.ouc.mallsecurity.model.PwdModel;
import com.ouc.mallsecurity.model.SignModel;

public interface SignService {

    /**
    * 向指定邮箱发送验证码
    *
    * @param email 接收验证码的邮箱
    * */
    void sendEmailCode(String email);

    /**
     * 通过账号密码来登录
     *
     * @param pwdModel 密码登陆对象
     * @return token
     * */
    String signInByPwd(PwdModel pwdModel);

    /**
     * 通过邮箱验证码来登录
     *
     * @param codeModel 验证码登陆对象
     * @return token
     * */
    String signInByCode(CodeModel codeModel);

    /**
     * 注册
     *
     * @param signModel 注册对象
     * */
    void signUp(SignModel signModel);
}
