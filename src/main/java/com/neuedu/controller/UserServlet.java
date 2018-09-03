package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.IpUtils;
import com.neuedu.common.MD5Utils;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应的格式，不同的servlet的响应格式会不同，无法统一写到过滤器里
        response.setContentType("text/html;charset=utf-8");
        //控制器作用：接收视图层的参数，调用业务逻辑层来处理
        //规定 username=XXX&password=XXX
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //如果为空，都不应该调用业务逻辑层---非空判断
        if(username==null || username.equals("")||password==null||password.equals("")){

            return;
        }

        IUserService userService = new UserServiceImpl();
        UserInfo userInfo = userService.login(username,password);
        //如果查询到信息
        if(userInfo!=null){
            //添加cookie信息
//            Cookie username_cookie = new Cookie(Const.USERNAMECOOKIE,username);
//            username_cookie.setMaxAge(60*60*24*7);
//            Cookie password_cookie = new Cookie(Const.PASSWORDCOOKIE,password);
//            password_cookie.setMaxAge(60*60*24*7);
//            response.addCookie(username_cookie);
//            response.addCookie(password_cookie);
            //token
            String token = IpUtils.getMACAddress(IpUtils.getRemoteAddress(request));
            System.out.println(token);
            Cookie tokenCookie = new Cookie(Const.TOKENCOOKIE, MD5Utils.GetMD5Code(token));
            tokenCookie.setMaxAge(7*24*3600);
            response.addCookie(tokenCookie);
            String sql = "update neuedu_user set token = ? where id = ?;";
            boolean updateFlag = DBUtils.updateDB(sql,MD5Utils.GetMD5Code(token),userInfo.getId());
            if(updateFlag){
                System.out.println("修改成功");
            }else{
                System.out.println("修改不成功");
            }

            //保存登录信息 keep the user login message in session
             HttpSession httpSession = request.getSession();
            httpSession.setAttribute(Const.CURRENTUSER,userInfo);
            request.getRequestDispatcher("manage/home.jsp").forward(request,response);
        }else{//用户名或密码输入错误 username or password error
            System.out.println("用户名或密码输入错误");
        }

    }
}
