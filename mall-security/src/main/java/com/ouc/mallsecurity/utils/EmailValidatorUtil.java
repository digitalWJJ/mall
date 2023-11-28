package com.ouc.mallsecurity.utils;

/**
 * @author 数码暴龙爆炸
 * 邮箱校验工具
 * */
public class EmailValidatorUtil {

    /**
     * 验证邮箱格式的正则表达式
     * */
    private static final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    /**
     * 验证邮箱格式是否正确
     *
     * @param email 需要被验证的邮箱
     * @return true or false
     * */
    public static boolean validateEmail(String email){
        return email.matches(regex);
    }
}
