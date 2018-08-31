package com.neuedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TestFilter")
public class TestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("====TestFilter====doFilter方法====");
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);//过滤器链  请求到达过滤器之后，可以让请求传递到下个过滤器
        //如果没有下一个过滤器，就传递到目标地
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println(" ==========filter===========init======");
    }

}
