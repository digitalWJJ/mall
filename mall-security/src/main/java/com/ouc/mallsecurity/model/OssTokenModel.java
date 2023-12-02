package com.ouc.mallsecurity.model;

import lombok.Data;

@Data
public class OssTokenModel {
    /**
     * RAM 用户的访问密钥 AccessKeyId 和 AccessKeySecret
     * */
    private String AccessKeyId;
    private String AccessKeySecret;

    /**
     * 临时身份令牌
     * */
    private String SecurityToken;

    /**
     * 临时身份令牌的到期时间
     * */
    private String Expiration;
}
