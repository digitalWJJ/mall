package com.ouc.mallsecurity.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallcommon.utils.TokenUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        String sign = RedisUtils.getActivatedUser(userId).getUserPwd();
        // 根据此用户的密码生成一个token验证器,也可以根据别的信息,取决于生成token时的设计
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        try{
            // 如果通过验证 包括但不限于有效时间之内和 sign 部分就可以正常获取到一个 DecodeJWT
            DecodedJWT decodedJWT = jwtVerifier.verify(accessToken);
            long userTimeStamp = decodedJWT.getExpiresAt().getTime(); // 获取该 token 的过期时间
            long currentTimeStamp = System.currentTimeMillis();
            long delta = (userTimeStamp-currentTimeStamp)/1000; // 以秒为单位的时间差
            if(  delta > 7200 ) throw new JWTVerificationException("无效token");
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            // TODO 可以考虑将 新的 token 接着用 json 数据返回 只要在 response 的 header 中设置一个标志位 然后 在加密的部分先判断一下, 如果为 true 就 新增加一个 token 字段到返回的 json 数据中
            if( delta < 1800 )  httpServletResponse.setHeader("Authorization", TokenUtils.genToken(String.valueOf(userId), sign) ); // 如果距离过期时间不足 30 分钟就重新生成一个token
        } catch (JWTVerificationException e){
            throw new ServiceException(401,e.getMessage());
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
