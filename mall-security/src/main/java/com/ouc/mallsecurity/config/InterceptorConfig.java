package com.ouc.mallsecurity.config;


import com.ouc.mallsecurity.interceptor.JwtInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        String[] excludePathPatterns = {"/getEmailCode", "/signInByPwd", "/signInByCode", "/signup"}; // 不进行拦截的路由
       /* registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
//                .excludePathPatterns("/signup");
//                .excludePathPatterns("/**");
        super.addInterceptors(registry);*/
    }
/*
    @Bean
    public JwtInterceptor jwtInterceptor(){ return new JwtInterceptor();}*/
}
