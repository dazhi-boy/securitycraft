package com.dazhi.securitycraft.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestFilter implements Filter {
    private Filter nextFilter;

    public Filter getNextFilter() {
        return nextFilter;
    }

    public void setNextFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        System.out.println("------下一个-------");
        if (null != nextFilter){
            nextFilter.doFilter(servletRequest,servletResponse,filterChain);
        }else {
            System.out.println("------路径不需要检验-------");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
