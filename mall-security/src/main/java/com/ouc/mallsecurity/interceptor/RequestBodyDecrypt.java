package com.ouc.mallsecurity.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ouc.mallsecurity.model.EnDeModel;
import com.ouc.mallsecurity.utils.AesUtil;
import com.ouc.mallsecurity.utils.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

//@ControllerAdvice
public class RequestBodyDecrypt implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 对所有目标是 RequestBody 标注的对象进行解密
     * */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                JSONObject jsonBody = getJsonObject(inputMessage.getBody()); // 获取请求体中的 json 数据
                EnDeModel enDeModel = JSONUtil.toBean(jsonBody, EnDeModel.class);
                String load = enDeModel.getLoad(); // 获取载荷 载荷部分是用 aseKey加密的
                String enAesKey = enDeModel.getEncryptedAesKey(); // 获取被公钥加密的 aseKey
                byte[] newBody = null;
                try {
                    String aseKey = new String( RsaUtil.decryptByPrivateKey( (Base64.decodeBase64(enAesKey.getBytes())), RsaUtil.getPrivateKey()) ); // 后端用私钥解密之后得到的 aseKey
                    String decryptedBody = AesUtil.decrypt(load, aseKey); // 使用aesKey解密 得到明文的 load
                    newBody = decryptedBody.getBytes(StandardCharsets.UTF_8); // 将明文数据重新写回到 body 中
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return new ByteArrayInputStream(newBody);
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };

    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 以 json 对象的格式获取 InputStream 中的数据
     * */
    private JSONObject getJsonObject(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
        return JSONUtil.parseObj( stringBuilder.toString() );
    }
}
