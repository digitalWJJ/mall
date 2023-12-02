package com.ouc.mallsecurity.tools;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ouc.mallcommon.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StsTool {

    private static String regionId;

    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    private static String roleArn;

    private static String roleSessionName;

    private static Long durationSeconds;

    /**
     * 获取 client
     * */
    public static DefaultAcsClient getClient(){
        try {
            // 添加endpoint。适用于Java SDK 3.12.0及以上版本。
            DefaultProfile.addEndpoint(regionId, "Sts", endpoint);
            // 构造 default profile
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            // 构造 client
            return new DefaultAcsClient(profile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(500,e.getMessage());
        }
    }

    /**
     * 获取扮演角色的请求体
     * */
    public static AssumeRoleRequest getAssumeRoleRequest(){
        final AssumeRoleRequest request = new AssumeRoleRequest();
        // 适用于Java SDK 3.12.0以下版本。
        request.setMethod(MethodType.POST);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);
        request.setDurationSeconds(durationSeconds);
        return request;
    }

    public static AssumeRoleResponse getAssumeRoleResponse(DefaultAcsClient client, AssumeRoleRequest assumeRoleRequest){
        try {
            return client.getAcsResponse(assumeRoleRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new ServiceException(500, e.getMessage());
        }
    }

    @Value("${sts.regionId}")
    public void setRegionId(String regionId) {
        StsTool.regionId = regionId;
    }

    @Value("${sts.endpoint}")
    public void setEndpoint(String endpoint) {
        StsTool.endpoint = endpoint;
    }

    @Value("${sts.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        StsTool.accessKeyId = accessKeyId;
    }

    @Value("${sts.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        StsTool.accessKeySecret = accessKeySecret;
    }

    @Value("${sts.roleArn}")
    public void setRoleArn(String roleArn) {
        StsTool.roleArn = roleArn;
    }

    @Value("${sts.roleSessionName}")
    public void setRoleSessionName(String roleSessionName) {
        StsTool.roleSessionName = roleSessionName;
    }

    @Value("${sts.durationSeconds}")
    public void setDurationSeconds(Long durationSeconds) {
        StsTool.durationSeconds = durationSeconds;
    }
}
