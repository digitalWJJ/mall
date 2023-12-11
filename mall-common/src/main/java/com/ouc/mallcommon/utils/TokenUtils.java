package com.ouc.mallcommon.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallmbg.mapper.UserMapper;
import com.ouc.mallmbg.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

public class TokenUtils {

    @Autowired
    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setStaticUserMapper(){
        staticUserMapper = userMapper;
    }

    /*
     * 生成token
     * */
    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization");
            if(StrUtil.isNotBlank(token)){
                token = token.replace("Bearer+","");
                String userId = JWT.decode(token).getAudience().get(0);
                return RedisUtils.getActivatedUser(Integer.parseInt(userId));
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(401, e.getMessage());
        }
        throw new ServiceException(401, "无效token");
    }
}
