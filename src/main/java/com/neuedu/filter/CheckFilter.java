package com.neuedu.filter;

import com.neuedu.businessconst.Const;
import com.neuedu.pojo.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤manage下面的任意路径
 * 后台管理页面，需要登录的权限
 */
@WebFilter("/manage/*")
public class CheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强转为子类
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取到Cookie
        HttpSession httpSession = request.getSession();
        Object object =  httpSession.getAttribute(Const.CURRENTUSER);
      //获取到登录信息
       if(object != null && object instanceof UserInfo){
           System.out.println("===check filter===yes=====");
           chain.doFilter(req, resp);

       }else{
           System.out.println("===check filter===no====");
           //未登录 重定向到登录页面
           response.sendRedirect("http://localhost:8080/BusinessWeb/login.jsp");
//           response.sendRedirect("login.jsp");
       }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
