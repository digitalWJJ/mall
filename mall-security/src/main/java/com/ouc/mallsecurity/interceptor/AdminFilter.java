package com.ouc.mallsecurity.interceptor;

import com.ouc.mallcommon.exception.ServiceException;
import com.ouc.mallcommon.utils.RedisUtils;
import com.ouc.mallcommon.utils.TokenUtils;
import com.ouc.mallmbg.model.User;
import jakarta.servlet.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * 对所有的管理员接口进行身份鉴权
 * */
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = TokenUtils.getCurrentUser();
        Boolean isUserRoot = RedisUtils.getActiveUserMap().get(user.getId()).getIsRoot();
        if( !isUserRoot ) throw new ServiceException(401, "非法访问");

        filterChain.doFilter(servletRequest, servletResponse);
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
