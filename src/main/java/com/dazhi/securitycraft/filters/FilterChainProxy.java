package com.dazhi.securitycraft.filters;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterChainProxy extends GenericFilterBean {
    List<Filter> filters = new ArrayList<>();
    public FilterChainProxy(List<Filter> filters){
        this.filters = filters;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        VirtualFilterChain vfc = new VirtualFilterChain(filterChain, filters);
        vfc.doFilter(servletRequest, servletResponse);
    }

    private static class VirtualFilterChain implements FilterChain {
        private final FilterChain filterChain;
        private final List<Filter> filters;
        private final int size;
        private int currentPosition = 0;

        public VirtualFilterChain(FilterChain filterChain, List<Filter> filters) {
            this.filterChain = filterChain;
            this.filters = filters;
            this.size = filters.size();
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
            if (currentPosition == size) {
//                currentPosition = 0;
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                Filter filter = filters.get(currentPosition);
                currentPosition++;
                filter.doFilter(servletRequest, servletResponse, this);
            }
        }
    }
}
