package com.neuedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 实现统计会员在线人数
 *
 */
//@WebListener
public class TestListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public TestListener() {
        System.out.println("====TestListener====构造器======");

    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // 全局域一旦初始化就回调该方法
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        System.out.println("====TestListener====contextInitialized======");

        ServletContext application = sce.getServletContext();
        application.setAttribute("count",0);
        System.out.println("当前在线人数"+application.getAttribute("count"));

    }
    // 全局域一旦销毁就回调该方法
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("====TestListener====contextDestroyed======");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    //session  创建
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        System.out.println("====TestListener====sessionCreated======");
    }
    //session  销毁
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        System.out.println("====TestListener====sessionDestroyed======");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    //会话域中添加属性
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
        System.out.println("====TestListener====attributeAdded======");
        ServletContext servletContext = sbe.getSession().getServletContext();
        int count = (int)servletContext.getAttribute("count");
        String name =  sbe.getName();
       if(name.equals("username")){
        servletContext.setAttribute("count",++count);
       }
       System.out.println("当前在线人数为"+servletContext.getAttribute("count"));
    }
    //会话域中移除某些属性
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
        System.out.println("====TestListener====attributeRemoved======");
        ServletContext servletContext = sbe.getSession().getServletContext();
        int count = (int)servletContext.getAttribute("count");
        String name =  sbe.getName();
        if(name.equals("username")){
            servletContext.setAttribute("count",--count);
        }
        System.out.println("当前在线人数为"+servletContext.getAttribute("count"));
    }
    //会话域中修改某些属性
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
        System.out.println("====TestListener====attributeReplaced======");
    }
    /*
    * ====构造方法==Hello Servlet=====
====init方法==Hello Servlet=====
====TestFilter====doFilter方法====
====dopost==Hello Servlet=====23
====doget==Hello Servlet=====23
====TestListener====attributeAdded======
当前在线人数为1
访问次数1
====TestListener====sessionCreated======
====TestFilter====doFilter方法====
====dopost==Hello Servlet=====11
====doget==Hello Servlet=====11
====TestListener====attributeAdded======
当前在线人数为2
访问次数2
====TestListener====sessionDestroyed======
====TestListener====attributeRemoved======
当前在线人数为1
====TestListener====sessionDestroyed======
====TestListener====attributeRemoved======
当前在线人数为0
    *
    * */
}
