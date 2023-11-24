package com.ouc.mallsecurity.interceptor;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ouc.mallcommon.AuthAccess;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;



public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(handler instanceof HandlerMethod){
            if( ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class) != null) return true;
        }

        String accessToken = request.getHeader("Authorization");
        if(StrUtil.isBlank(accessToken) || !accessToken.startsWith("Bearer+")) throw new ServiceException(401, "请登录"); // 判断 Authorization 中是否以 bearer 作为开头
        accessToken = accessToken.replace("Bearer+", ""); // 将 Bearer+ 替换为空
        if(StrUtil.isBlank(accessToken)) throw new ServiceException(401, "请登录"); // 验证token是否为空

        // 解码token获取其中的userId
        int userId;
        try {
            userId = Integer.getInteger( JWT.decode(accessToken).getAudience().get(0) ); // 这里具体获取什么内容取决于你是如何向其中填入内容的
        } catch (JWTDecodeException e){
            throw new ServiceException(401, "请登录");
        }

        // 根据userId取得对应用户的具体信息
        User user = userMapper.selectByPrimaryKey( userId );
        if(ObjUtil.isEmpty(user)) throw new ServiceException(406, "该用户不存在");

        // 根据此用户的密码生成一个token验证器,也可以根据别的信息,取决于生成token时的设计
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPwd())).build();
        try{
            jwtVerifier.verify(accessToken);
        } catch (JWTVerificationException e){
            throw new ServiceException(401,"请登录");
        }

        return true; // 前面的步骤都没问题就返回真
    }
}
