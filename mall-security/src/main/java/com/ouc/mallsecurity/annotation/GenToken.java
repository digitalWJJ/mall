package com.ouc.mallsecurity.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 生成 token 的注解
 * */
@Target(METHOD)
@Retention(RUNTIME)
public @interface GenToken {
}
