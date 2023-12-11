package com.ouc.mallsecurity.config;


import com.ouc.mallsecurity.interceptor.AdminInterceptor;
import com.ouc.mallsecurity.interceptor.CrossDomainInterceptor;
import com.ouc.mallsecurity.interceptor.JwtInterceptor;
import com.ouc.mallsecurity.interceptor.ReplayAttackInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        String[] jwtExcludePathPatterns = {"/api/getEmailCode", "/api/signInByPwd", "/api/signInByCode", "/api/signup","/api/admin/signup"}; // 不进行拦截的路由

        // 添加解决跨域的拦截器
        registry.addInterceptor(crossDomainInterceptor())
                        .addPathPatterns("/**");

        // 添加放重放攻击的拦截器
        registry.addInterceptor(replayAttackInterceptor())
                        .addPathPatterns("/api/**");

        // 添加 jwtToken 拦截器
        registry.addInterceptor(jwtInterceptor())
                        .addPathPatterns("/api/**")
                        .excludePathPatterns(jwtExcludePathPatterns);

        // 添加管理员权限认证拦截器
        registry.addInterceptor(adminInterceptor())
                        .addPathPatterns("/api/admin/**");
        super.addInterceptors(registry);
    }

    /**
     * jwtToken 认证拦截器
     * */
    @Bean
    public JwtInterceptor jwtInterceptor(){ return new JwtInterceptor();}

    /**
     * 解决跨域的拦截器
     * */
    @Bean
    public CrossDomainInterceptor crossDomainInterceptor(){ return new CrossDomainInterceptor(); }

    /**
     * 管理员权限的拦截器
     * */
    @Bean
    public AdminInterceptor adminInterceptor(){ return new AdminInterceptor(); }

    @Bean
    public ReplayAttackInterceptor replayAttackInterceptor(){ return new ReplayAttackInterceptor(); }
}
