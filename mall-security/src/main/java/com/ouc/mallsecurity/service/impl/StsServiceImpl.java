package com.ouc.mallsecurity.service.impl;

import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.ouc.mallsecurity.model.OssTokenModel;
import com.ouc.mallsecurity.service.StsService;
import com.ouc.mallsecurity.tools.StsTool;
import org.springframework.stereotype.Service;

@Service
public class StsServiceImpl implements StsService {
    @Override
    public OssTokenModel getOssToken() {
        OssTokenModel ossTokenModel = new OssTokenModel();
        // 获取从 sts 得到的角色扮演返回
        AssumeRoleResponse assumeRoleResponse = StsTool.getAssumeRoleResponse(StsTool.getClient(), StsTool.getAssumeRoleRequest());

        ossTokenModel.setAccessKeyId(assumeRoleResponse.getCredentials().getAccessKeyId());
        ossTokenModel.setAccessKeySecret(assumeRoleResponse.getCredentials().getAccessKeySecret());
        ossTokenModel.setSecurityToken(assumeRoleResponse.getCredentials().getSecurityToken());
        ossTokenModel.setExpiration(assumeRoleResponse.getCredentials().getExpiration());

        return ossTokenModel;
    }
}
