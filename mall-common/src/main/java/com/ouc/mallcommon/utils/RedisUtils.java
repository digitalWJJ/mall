package com.ouc.mallcommon.utils;

import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author zhuhuix
 * @date 2020-06-15
 */
@Component
@AllArgsConstructor
public class RedisUtils {
    private static RedisTemplate<Object, Object> redisTemplate;

    private static final String activatedUserMap = "activatedUserMap";
    private static final String productsMap = "productsMap";
    @Autowired
    RedisTemplate<Object, Object> redisTemplateInit;

    @Autowired
    ProductMapper productMapper;

    @PostConstruct
    private void setRedisTemplate (){
        redisTemplate = redisTemplateInit;
    }

    /**
     * 普通获取键对应值
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通设置键值
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 普通设置键值并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return 是否成功
     */
    public static boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key  键值 @NotNull
     * @param time 时间(秒) @NotNull
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取指定的 id 用户
     * @param id 指定的用户的 id
     * */
    public static User getActivatedUser(int id){
        try {
            User user = (User) redisTemplate.opsForHash().get(activatedUserMap, id);
            if(user == null) throw new ServiceException("请重新登录");
            return user;
        } catch (Exception e) {
            if(e instanceof ServiceException) throw new ServiceException(401, e.getMessage());
            else throw new ServiceException(500, e.getMessage());
        }
    }

    /**
     * 设置一个用户到 activateUserMap 中
     * @param user 需要被添加的 user
     * */
    public static void setUser2ActivatedUserMap(User user){
        try {
            redisTemplate.opsForHash().put(activatedUserMap, user.getId(), user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(500, e.getMessage());
        }
    }
}
