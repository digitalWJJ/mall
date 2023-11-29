package com.ouc.mallsecurity.interceptor;

import com.ouc.mallsecurity.utils.BodyRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 对 json 格式的请求进行加密和解密
 * */
public class DeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String enContent = getBody(request); // 获取请求体参数中加密的原始数据
        // 在修饰器里做两个动作，解密然后修改请求体
        BodyRequestWrapper requestWrapper = new BodyRequestWrapper(request, enContent);

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 以 json 字符串的格式获取 Request 中的 body 部分的数据
     * */
    private String getBody(ServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
        return stringBuilder.toString();
    }
}
