package com.ouc.mallsecurity.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallcommon.utils.RedisUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * token 认证 filter
 * */
public class JwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String accessToken = request.getHeader("Authorization");
        if(StrUtil.isBlank(accessToken) || !accessToken.startsWith("Bearer+")) throw new ServiceException(401, "请登录"); // 判断 Authorization 中是否以 bearer 作为开头
        accessToken = accessToken.replace("Bearer+", ""); // 将 Bearer+ 替换为空
        if(StrUtil.isBlank(accessToken)) throw new ServiceException(401, "请登录"); // 验证token是否为空

        // 解码token获取其中的userId
        int userId;
        try {
            userId = Integer.parseInt( JWT.decode(accessToken).getAudience().get(0) ); // 这里具体获取什么内容取决于你是如何向其中填入内容的
        } catch (JWTDecodeException e){
            throw new ServiceException(401, "请登录");
        }

        String sign = RedisUtils.get(String.valueOf(userId)).toString();
        // 根据此用户的密码生成一个token验证器,也可以根据别的信息,取决于生成token时的设计
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        try{
            jwtVerifier.verify(accessToken);
        } catch (JWTVerificationException e){
            throw new ServiceException(401,"请登录");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
