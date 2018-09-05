package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.IpUtils;
import com.neuedu.common.MD5Utils;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.exception.BusinesseLoginException;
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
        HttpSession httpSession = request.getSession();
        //设置操作符对应的操作方法
        String operation = request.getParameter("operation");
        //如果传入的操作符为空 ，抛异常
        if (operation == null || operation.equals("")) {
            throw BusinesseLoginException.createException(httpSession, "操作符不能为空", "3s后跳转到登录界面", "login.jsp");
        }

        /* 根据操作符判断操作方法
         * 1 登录 login
         * 2  注册  register
         * 3 修改密码
         * 4 修改用户信息
         * 5 登录状态下重置密码
         */
        if (operation.equals("1")) {
            login(request, response);
        } else if (operation.equals("2")) {
            register(request, response);
        } else if (operation.equals("3")) {
            login(request, response);
        } else if (operation.equals("4")) {
            login(request, response);
        } else if (operation.equals("5")) {
            login(request, response);
        }
    }




    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求参数
        HttpSession httpSession = request.getSession();
        String username = request.getParameter("username");//  '用户名',
        String password = request.getParameter("password");//   '用户密码，MD5加密',
        String email = request.getParameter("email");//  用户邮箱
        String phone = request.getParameter("phone");//  用户电话
        String question = request.getParameter("question");//   '找回密码问题',
        String answer = request.getParameter("answer");//   '找回密码答案',
        Integer role = 1;

        if (username == null || username.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "用户名不能为空", "3s后跳转到注册页面", "register.jsp");
        }
        if (password == null || password.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "密码不能为空", "3s后跳转到注册页面", "register.jsp");
        }
        if (email == null || email.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "邮箱不能为空", "3s后跳转到注册页面", "register.jsp");
        }
        if (phone == null || phone.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "手机号不能为空", "3s后跳转到注册页面", "register.jsp");
        }
        if (question == null || question.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "找回密码问题不能为空", "3s后跳转到注册页面", "register.jsp");
        }
        if (answer == null || answer.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "找回密码问题答案不能为空", "3s后跳转到注册页面", "register.jsp");
        }
        if (role == null || role.equals("")) {
            throw BusinesseLoginException.createException(request.getSession(), "用户属性不能为空", "3s后跳转到注册页面", "register.jsp");
        }

        IUserService userService = new UserServiceImpl();

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfo.setQuestion(question);
        userInfo.setAnswer(answer);
        userInfo.setRole(role);

       boolean result= userService.register(httpSession,userInfo);
       if(result){
           System.out.println("注册成功");
           throw BusinesseLoginException.createException(httpSession,"注册成功","3s后跳转到登录页面","login.jsp");
       }else{
           throw BusinesseLoginException.createException(httpSession,"注册失败","3s后跳转回注册页面","register.jsp");

       }
    }
//localhost:8080/BusinessWeb/user?operation=2&username=xixi&password=123&email=2312@163.com&phone=11111&question=***&answer=1212
    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应的格式，不同的servlet的响应格式会不同，无法统一写到过滤器里
        response.setContentType("text/html;charset=utf-8");
        HttpSession httpSession = request.getSession();
        //控制器作用：接收视图层的参数，调用业务逻辑层来处理
        //规定 username=XXX&password=XXX
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //如果为空，都不应该调用业务逻辑层---非空判断
        if (username == null || username.equals("") || password == null || password.equals("")) {
            throw BusinesseLoginException.createException(httpSession, "用户名密码不能为空", "3s后跳转到登录界面", "login.jsp");
        }

        IUserService userService = new UserServiceImpl();
        UserInfo userInfo = userService.login(username, password);
        //如果查询到信息
        if (userInfo != null) {

            //token
            String token = IpUtils.getMACAddress(IpUtils.getRemoteAddress(request));
            System.out.println(token);
            Cookie tokenCookie = new Cookie(Const.TOKENCOOKIE, MD5Utils.GetMD5Code(token));
            tokenCookie.setMaxAge(7 * 24 * 3600);
            response.addCookie(tokenCookie);
            String sql = "update neuedu_user set token = ? where id = ?;";
            boolean updateFlag = DBUtils.updateDB(sql, MD5Utils.GetMD5Code(token), userInfo.getId());
            if (updateFlag) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改不成功");
            }

            //保存登录信息 keep the user login message in session

            httpSession.setAttribute(Const.CURRENTUSER, userInfo);
            request.getRequestDispatcher("manage/home.jsp").forward(request, response);
        } else {//用户名或密码输入错误 username or password error
            System.out.println("用户名或密码输入错误");
        }

    }
}
