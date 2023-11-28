package com.ouc.mallsecurity.interceptor;

import com.ouc.mallcommon.exception.ServiceException;
import jakarta.servlet.*;

import java.io.IOException;

public class ExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e){
            if(e instanceof ServiceException){
                e.printStackTrace();
                servletRequest.setAttribute("filterError", e);
                servletRequest.getRequestDispatcher("/error/throw").forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
