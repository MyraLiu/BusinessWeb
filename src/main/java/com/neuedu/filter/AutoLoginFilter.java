package com.neuedu.filter;

import com.neuedu.businessconst.Const;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/manage/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强转为子类
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取到Cookie
       Cookie[] cookies = request.getCookies();
       String username = null;
       String password = null;
       String token = null;
        HttpSession session = request.getSession();
       if(cookies != null){
           for (Cookie cookie:cookies) {
               String cookieName = cookie.getName();
//               if(cookieName.equals(Const.USERNAMECOOKIE)){
//                   username = cookie.getValue();
//               }
//               if(cookieName.equals(Const.PASSWORDCOOKIE)){
//                   password = cookie.getValue();
//               }
               if(cookieName.equals(Const.TOKENCOOKIE)){
                   token = cookie.getValue();
               }
           }
       }
       if(token != null){
           IUserService userService = new UserServiceImpl();
           UserInfo userInfo = userService.autoLogin(token);
           if(userInfo!=null ){
               session.setAttribute(Const.CURRENTUSER,userInfo);
               chain.doFilter(req, resp);
               System.out.println("====autologinfilter======yes");
               return;
           }
       }else{
           System.out.println("====autologinfilter======no");
           response.sendRedirect("http://localhost:8080/BusinessWeb/login.jsp");
    }

//        System.out.println(username+"  "+password);
//       if(username != null && password != null){
//           IUserService userService = new UserServiceImpl();
//           UserInfo userInfo = userService.login(username,password);
//           if(userInfo != null){
//
//               session.setAttribute(Const.CURRENTUSER,userInfo);
//               chain.doFilter(req, resp);
//               System.out.println("====autologinfilter======yes");
//                return;
//           }
//       }else{
//           System.out.println("====autologinfilter======no");
//           response.sendRedirect("http://localhost:8080/BusinessWeb/login.jsp");
//       }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
