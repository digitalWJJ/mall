package com.ouc.mallcommon.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.ouc.mallcommon.Result;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

@Component
public class OSS {
    @Value("${oss.bucket}")
    private String bucketName;
    private static String bucket;
    @Autowired
    private OSSClient ossClient;
    private static OSSClient itOssClient;
    static String productImgDirname="productImg/";

    static String userImgDirname="userImg/";

    @PostConstruct
    public void init() {
        itOssClient=ossClient;
        bucket=bucketName;
    }
    public static String getProductImgUrl(String imgName,int type) throws ClientException {
        String key="";
        if(type==0)key=productImgDirname+imgName;
        else if(type==1)key=userImgDirname+imgName;
        else return "error";
        // 设置URL过期时间为1小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest ;
        generatePresignedUrlRequest =new GeneratePresignedUrlRequest(bucket, key);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = itOssClient.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    public static Result uploadImg(String objectName, byte[] content, int type) throws ClientException {
        String key="";
        if(type==0)key=productImgDirname+objectName;
        else if(type==1)key=userImgDirname+objectName;
        else return new Result(500,"",null);
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, new ByteArrayInputStream(content));
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);
            PutObjectResult result = itOssClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            if (itOssClient != null) {
                itOssClient.shutdown();
            }
        }
        return new Result(200,"上传图片成功",null);
    }
}
