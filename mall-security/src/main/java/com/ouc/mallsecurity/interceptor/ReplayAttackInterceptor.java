package com.ouc.mallsecurity.interceptor;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.StrUtil;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallsecurity.utils.RsaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ReplayAttackInterceptor implements HandlerInterceptor {

    private final long requestIdExpiration = 60; // 请求id的过期时间为60秒

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中拿出 timeStamp  requestId signature clientPublicKey
        String timeStamp = request.getHeader("TimeStamp");
        String requestId = request.getHeader("RequestId");
        String signature = request.getHeader("Signature");
        String clientPublicKey = URLDecoder.decode(request.getHeader("ClientPublicKey"), StandardCharsets.UTF_8);
        if( StrUtil.isBlank(timeStamp) || StrUtil.isBlank(requestId) || StrUtil.isBlank(signature) || StrUtil.isBlank(clientPublicKey)) throw new ServiceException(403, "请求配置错误");

        if( Math.abs(Long.parseLong(timeStamp) - System.currentTimeMillis())/1000 > requestIdExpiration ) throw new ServiceException(403, "请求超时"); // 请求的时间戳不可以超过 60 秒
        if(RedisUtils.get(requestId) != null) throw new ServiceException(403, "重复请求"); // 请求的requestId 不能在缓存中重复


        byte[] sign = Base64.getMimeDecoder().decode(signature); // utf8 -> base64 bytes -> original bytes
        byte[] msg = (timeStamp+requestId).getBytes(StandardCharsets.UTF_8); // 被签名的消息 utf8 -> original bytes
        // 将Base64编码的公钥和签名转换为字节数组
        byte[] publicKeyBytes = Base64.getDecoder().decode(clientPublicKey); // utf8 -> base64 bytes -> original bytes
        boolean verified = RsaUtil.verifySignature(msg, sign, publicKeyBytes);

        if(!verified) throw new ServiceException(403, "签名未通过"); // 验证未通过

        RedisUtils.set(requestId, requestId, requestIdExpiration);
        return false;
    }

}
