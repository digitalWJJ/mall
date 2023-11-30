package com.ouc.mallcommon.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * OSS对象存储相关配置
 * Created by macro on 2018/5/17.
 */
@Component
public class OssConfig {
    @Value("${oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${OSS_ACCESS_KEY_ID}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${OSS_ACCESS_KEY_SECRET}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;
    @Bean
    public OSSClient ossClient(){
        return new OSSClient(ALIYUN_OSS_ENDPOINT,ALIYUN_OSS_ACCESSKEYID,ALIYUN_OSS_ACCESSKEYSECRET);
    }
}
