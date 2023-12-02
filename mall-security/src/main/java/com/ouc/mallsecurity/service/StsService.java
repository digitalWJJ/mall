package com.ouc.mallsecurity.service;

import com.ouc.mallsecurity.model.OssTokenModel;

public interface StsService {

    /**
     * 获取访问 OSS 的临时身份令牌
     * */
    public OssTokenModel getOssToken();
}
