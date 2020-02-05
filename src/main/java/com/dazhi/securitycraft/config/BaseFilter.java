package com.dazhi.securitycraft.config;

import com.dazhi.securitycraft.filters.FilterCookie;
import com.dazhi.securitycraft.filters.FilterToken;
import com.dazhi.securitycraft.filters.TestFilter;
import com.dazhi.securitycraft.filters.FilterChainProxy;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter
public class BaseFilter implements Filter {
    private static List<Filter> filter = new ArrayList();
    FilterCookie cookieFilter = new FilterCookie();
    FilterToken tokenFilter = new FilterToken();
    TestFilter testFilter = new TestFilter();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filter.add(cookieFilter);
        filter.add(tokenFilter);
        filter.add(testFilter);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterChainProxy filterChainProxy = new FilterChainProxy(filter);
        filterChainProxy.doFilter(servletRequest,servletResponse,filterChain);
    }

    @Override
    public void destroy() {

    }
}
