package com.neuedu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session.do")
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 这个方法还没验证
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        request.setAttribute("nature","nat");
        String sessionId = session.getId();
        PrintWriter writer = response.getWriter();
        if(session.isNew()){

          writer.print ("新建一个会话");
        }
       writer.print("sessionid="+sessionId);
//        int maxTime = session.getMaxInactiveInterval();
//        writer.print("过期时间="+maxTime+"<br/>");
//        session.setMaxInactiveInterval(10);
//        writer.print("过期时间="+maxTime+"<br/>");
//        session.invalidate();//会话立刻失效
//        writer.print("换行了吗");//测试<br/>效果

    }
    
}
