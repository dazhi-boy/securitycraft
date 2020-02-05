package com.dazhi.securitycraft.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterCookie implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if(uri.startsWith("/a")){//验证cookie
            Cookie[] cookies = request.getCookies();
            if (null!= cookies && cookies.length>0){
                System.out.println("------存在cookie，可以访问数据-------");
            }else {
                System.out.println("------没有cookie，返回错误信息-------");
            }
        }else {
            System.out.println("------路径不需要检验-------");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
