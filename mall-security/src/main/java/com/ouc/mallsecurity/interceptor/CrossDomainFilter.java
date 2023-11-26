package com.ouc.mallsecurity.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 解决跨域请求的 filter
 * */
public class CrossDomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 添加参数，允许任意domain访问
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 这个allow-headers要配为*，这样才能允许所有的请求头 --- update by zxy  in 2018-10-19
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
//        resp.setHeader("Access-Control-Max-Age", time+"");

        filterChain.doFilter(servletRequest, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
