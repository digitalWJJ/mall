package com.ouc.mallsecurity.interceptor;

import cn.hutool.json.JSONUtil;
import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallcommon.utils.TokenUtils;
import com.ouc.mallmbg.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * 生成 token 的切面
 * */
@Component
@Aspect
public class AfterSignInAspect {

    /**
     * 用户登录之将用户信息添加到 activeUsers 中,然后生成 token
     * */
    @Around(value = "@annotation(com.ouc.mallsecurity.annotation.GenToken)")
    public String afterSignInAspect(ProceedingJoinPoint joinPoint ) throws Throwable {
        String pointResult = (String) joinPoint.proceed();
        User user = string2User(pointResult);
        setUser2Redis(user);
        return genToken(String.valueOf( user.getId() ), user.getUserPwd());
    }

    /**
     * 生成token
     * @param userId 用户id
     * @param sign 认证信息
     * */
    public String genToken(String userId, String sign){
        return TokenUtils.genToken(userId, sign);
    }

    /**
     * 将新登陆的用户添加到缓存中
     * @param user 被添加到缓存的用户对象
     * */
    public void setUser2Redis(User user){
        RedisUtils.getActiveUserMap().put(user.getId(), user);
    }

    /**
     * string 对象转为 javaBean
     * @param strUser User 对象的字符串表示
     * */
    public User string2User(String strUser){
        String jsonStrUser =  strUser.replace("[", "{")
                .replace( "]", "}")
                .replace("=", ":")
                .replace("User","") // TODO 找个正则表达式 匹配 User 这个单词
                .replaceAll("\r|\n|\\s*", "");
        return JSONUtil.toBean(jsonStrUser, User.class);
    }
}
