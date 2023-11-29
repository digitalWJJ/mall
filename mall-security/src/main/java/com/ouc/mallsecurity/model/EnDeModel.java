package com.ouc.mallsecurity.model;

import lombok.Data;

@Data
public class EnDeModel {
    /**
     * 被加密的 aesKey
     * */
    private String encryptedAesKey;

    /**
     * 载荷部分
     * */
    private String load;
}
