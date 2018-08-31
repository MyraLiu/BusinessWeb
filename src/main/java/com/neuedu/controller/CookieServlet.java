package com.neuedu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie.do")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("username","admin");
        response.addCookie(cookie);
        PrintWriter writer = response.getWriter();
        //获取响应头中的cookie信息
        Cookie[] cookies = request.getCookies();
        for(Cookie c:cookies){
            String cname = c.getName();
            String cvalue = c.getValue();
            writer.print("name="+cname+" cvalue="+cvalue+"<br/>");
        }
        //cookie在客户端保存周期
        //cookie 的生存周期
//        cookie.setMaxAge(7*24*3600);
        //

    }
}
