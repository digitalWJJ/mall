package com.ouc.mallsecurity.interceptor;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ouc.mallsecurity.model.EnDeModel;
import com.ouc.mallsecurity.utils.AesUtil;
import com.ouc.mallsecurity.utils.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.StandardCharsets;

/**
 * 拦截所有的 Result 让其在返回之前将数据进行加密
 *
 * string   ->   string   ->   byte   ->   byte   ->   byte   ->   string
 * 明文string   aes加密之后的   转为byte    rsa加密的     base64转码的   传输的
 * */
@ControllerAdvice
public class ResponseBodyEncrypt implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 在数据被到 response 对象中之前对数据进行加密
     * */
    @Override
    public JSONObject beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        response.getHeaders().set("Access-Control-Expose-Headers", "ServerPublicKey"); // 解决axios 无法受到自定义的请求头
        response.getHeaders().set("ServerPublicKey", RsaUtil.getPublicKey()); // 在响应头中设置服务器端的公钥
        String jsonString = JSONUtil.toJsonStr(body);
        String aesKey = AesUtil.getKey(); // aesKey 每一次 get 都是新的 所以需要先缓存
        System.out.println(aesKey); // TODO 打印输出
        EnDeModel enDeModel = new EnDeModel();
        try {
            String load = AesUtil.encrypt(jsonString, aesKey);
            enDeModel.setLoad(load); // 设置载荷部分
            String ClientPublicKey = URLDecoder.decode( request.getHeaders().getFirst("ClientPublicKey"), StandardCharsets.UTF_8);
            if(ClientPublicKey == null) return null; // 没有客户端公钥就返回null
            byte[] enAesKey = RsaUtil.encryptByPublicKey(aesKey.getBytes(StandardCharsets.UTF_8), ClientPublicKey); // 获取加密后的 byte
            enDeModel.setEncryptedAesKey(Base64.encodeBase64String(enAesKey));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return JSONUtil.parseObj(enDeModel);
    }
}
