package com.dazhi.securitycraft.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterToken implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.startsWith("/b")) {//验证token
            String authentication = (String) request.getHeader("Authentication");
            if (null!= authentication){
                System.out.println("------token，可以访问数据-------");
            }else {
                System.out.println("------没有token，返回错误信息-------");
            }
        }else {
            System.out.println("------路径不需要检验-------");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
