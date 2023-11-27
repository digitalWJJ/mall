package com.ouc.mallsecurity.service;

import com.ouc.mallsecurity.model.CodeModel;
import com.ouc.mallsecurity.model.PwdModel;
import com.ouc.mallsecurity.model.SignModel;

public interface SignService {

    /*
    * 向指定邮箱发送验证码
    * */
    void sendEmailCode(String email);

    /*
    * 通过账号密码来登录
    * @return token
    * */
    String signInByPwd(PwdModel pwdModel);

    /*
    * 通过邮箱验证码来登录
    * @return token
    * */
    String signInByCode(CodeModel codeModel);

    /*
    * 注册
    * */
    void signUp(SignModel signModel);
}
