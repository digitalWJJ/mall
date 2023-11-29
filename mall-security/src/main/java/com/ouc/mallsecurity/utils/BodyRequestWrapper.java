package com.ouc.mallsecurity.utils;

import cn.hutool.core.stream.StreamUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
/**
 * HttpServletRequest修饰器，修改原 request 对象中的 body 中的数据
 * */
public class BodyRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;
    public BodyRequestWrapper(HttpServletRequest request, String jsonStr ) throws IOException     {
        super(request);
        // 解密 json 字符串
        jsonStr = jsonStr.replaceAll("\r|\n|\\s*", ""); // jsonStr 中的每个字段的内容都是被加密过的
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr); // 将 json 字符串转为 jsonObject
        String load = jsonObject.get("load").toString(); // 获取 json 中的载荷 载荷部分是用 aseKey加密的
        String enAesKey = jsonObject.get("aesKey").toString(); // 获取被公钥加密的 aseKey
        try {
            String aseKey = new String( RsaUtil.decryptByPrivateKey( (Base64.decodeBase64(enAesKey.getBytes())), RsaUtil.getPrivateKey()) ); // 后端用私钥解密之后得到的 aseKey
            String decrypt = AesUtil.decrypt(load, aseKey); // 使用aesKey解密 得到明文的 load
            body = decrypt.getBytes(); // 将明文数据重新写回到 body 中
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 在使用@RequestBody注解的时候，其实框架是调用了getInputStream()方法，所以我们要重写这个方法，将原来的 inputStream 封装为可以重复读取的ByteArrayInputStream
     * @return 对新的 body 封装过的 ByteArrayInputStream
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }


}
