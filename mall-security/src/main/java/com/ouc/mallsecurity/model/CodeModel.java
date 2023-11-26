package com.ouc.mallsecurity.model;

import lombok.Data;

@Data
public class CodeModel {
    /**
     * 邮箱
     * */
    String email;

    /**
     * 验证码
     * */
    String code;
}
