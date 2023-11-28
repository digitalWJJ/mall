package com.ouc.mallsecurity.model;

import lombok.Data;

@Data
public class SignModel {
    /**
     * 邮箱
     * */
    private String email;

    /**
     * 密码
     * */
    private String pwd;

    /**
     * 确认密码
    * */
    private String confirmPwd;

    /**
     * 验证码
     * */
    private String code;
}
