package com.neuedu.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 练习使用
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public HelloServlet(){
        System.out.println("====构造方法==Hello Servlet=====");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("====init方法==Hello Servlet=====");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("====dopost==Hello Servlet====="+request.getParameter("username"));
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("====doget==Hello Servlet====="+request.getParameter("username"));
//       request.setAttribute("username","admin");
        //请求分派
//        request.getRequestDispatcher("login.jsp").forward(request,response);
        //重定向
//        response.sendRedirect("login.jsp");
//        Alt+回车  导包快捷键
//        session域
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username","admin");
        httpSession.setMaxInactiveInterval(10);
//        统计访问helloservlet的次数   全局域
        ServletContext application =  this.getServletContext();

//        Object count = application.getAttribute("count");
//        if(count == null){
//            application.setAttribute("count",1);
//        }else{
//            int count1 = (int)count;
//            application.setAttribute("count",++count1);
//        }
        System.out.println("访问次数"+application.getAttribute("count"));
//        response.sendRedirect("login.jsp");
    }



    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
        System.out.println("====doTrace==Hello Servlet=====");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        System.out.println("====doOptions==Hello Servlet=====");
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
        System.out.println("====doHead==Hello Servlet=====");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        System.out.println("====doPut==Hello Servlet=====");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        System.out.println("====doDelete==Hello Servlet=====");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("====destroy===hello Servlet=====");
    }
}
