package com.ouc.mallsecurity.interceptor;

import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallcommon.utils.TokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 生成 token 的切面
 * */
@Component
@Aspect
public class GenTokenAspect {

    @Around(value = "@annotation(com.ouc.mallsecurity.annotation.GenToken)")
    public String genToken(ProceedingJoinPoint joinPoint ) throws Throwable {
        String user = (String) joinPoint.proceed();
        String[] info = user.split("-");
        RedisUtils.set(info[0], info[1]);
        return TokenUtils.genToken(info[0], info[1]);
    }
}
