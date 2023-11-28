package com.ouc.mallsecurity.config;

import com.ouc.mallsecurity.interceptor.CrossDomainFilter;
import com.ouc.mallsecurity.interceptor.EnDeFilter;
import com.ouc.mallsecurity.interceptor.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     * 处理 filter 中的错误路由的页面
     *
     */
    @Bean
    public FilterRegistrationBean<ExceptionFilter> exceptionFilter() {
        FilterRegistrationBean<ExceptionFilter> bean = new FilterRegistrationBean<>();
        bean.setName("exceptionFilter");
        bean.setOrder(0); // 这里要尽可能地小, 在所有的 filter 之前执行
        bean.setFilter(new ExceptionFilter());
        return bean;
    }

    /**
     * 解决跨域问题的 filter的配置
     * */
    @Bean
    public FilterRegistrationBean<CrossDomainFilter> configCrossDomainFilter(){
        FilterRegistrationBean<CrossDomainFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(1);
        bean.setFilter(new CrossDomainFilter());
        // 匹配"/hello/"下面的所有url
        bean.addUrlPatterns("/**");
        return bean;
    }

    /**
     * token 认证的 filter
     * */
    @Bean
    public FilterRegistrationBean<JwtFilter> configJwtFilter(){
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(2);
        bean.setFilter(new JwtFilter());
        // 匹配"/hello/"下面的所有url
        bean.addUrlPatterns("/testNothing");
        return bean;
    }

    /**
     * json 数据加密和解密的 filter
     * */
    @Bean
    public FilterRegistrationBean<EnDeFilter> configEnDeFilter(){
        FilterRegistrationBean<EnDeFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(3);
        bean.setFilter(new EnDeFilter());
        // 匹配"/hello/"下面的所有url
        bean.addUrlPatterns("/testRequest");
        return bean;
    }
}
