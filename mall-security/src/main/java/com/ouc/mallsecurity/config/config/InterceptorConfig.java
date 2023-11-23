package com.ouc.mallsecurity.config.config;


import com.ouc.mallsecurity.interceptor.JwtInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@MapperScan("com.ouc.mallmbg.mapper")
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/signup");
//                .excludePathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){ return new JwtInterceptor();}
}
