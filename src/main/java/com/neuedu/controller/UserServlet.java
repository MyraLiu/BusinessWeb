package com.neuedu.controller;

import com.google.gson.Gson;
import com.neuedu.businessconst.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.IpUtils;
import com.neuedu.common.MD5Utils;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.exception.BusinesseLoginException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;

import javax.management.StringValueExp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    /**
     * post 方法  调用get
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * get方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
        System.out.println("操作符：" + operation);
        /* 根据操作符判断操作方法
         * 1 登录 login
         * 2  注册  register
         * 3 修改密码
         * 4 修改用户信息
         * 5 登录状态下重置密码
         */
        if (operation.equals("1")) { // 登录
            login(request, response);
        } else if (operation.equals("2")) { // 注册
            register(request, response);
        } else if (operation.equals("3")) { // 找回密码-获取问题
            findQuestionByUsername(request, response);
        } else if (operation.equals("4")) { // 找回密码-校验答案
            checkAnswer(request, response);
        } else if (operation.equals("5")) { // 找回密码-修改密码
            updatePassword(request, response);
        } else if (operation.equals("6")) { // 登录状态下修改密码
            changePassword(request, response);
        } else if (operation.equals("7")) { // 获取个人信息
            selfInfo(request, response);
        } else if (operation.equals("8")) { // 修改个人信息
            updateSelfInfo(request, response);
        } else if (operation.equals("9")) { // 退出登录
            logout(request, response);
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====exit=======");
        //获取请求参数
        HttpSession session = request.getSession();
        IUserService userService = new UserServiceImpl();

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (user != null) {
            session.removeAttribute(Const.CURRENTUSER);
            //清除用户的token
            userService.updateTokenById(user.getId(), "");
        }
        session.invalidate();
        response.sendRedirect("index.jsp");


    }


    protected void updateSelfInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====updateSelfInfo=======");
        //获取请求参数
        HttpSession session = request.getSession();
        String username = request.getParameter("username");//  '用户名',
        String oldpassword = request.getParameter("oldpassword");//  '用户名',
        String newpassword = request.getParameter("newpassword");//  '用户名',

        //非空验证
        if (username == null || username.equals("")) {
            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到输入密码页面", "answer.jsp");
        }
        if (oldpassword == null || oldpassword.equals("")) {
            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到输入密码页面", "answer.jsp");
        }
        if (newpassword == null || newpassword.equals("")) {
            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到输入密码页面", "answer.jsp");
        }
        // TODO logic service develop

    }


    protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====changePassword=======");
        //获取请求参数
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        String username = request.getParameter("username");//  '用户名',
        String oldpassword = request.getParameter("oldpassword");//  '原始密码',
        String newpassword = request.getParameter("newpassword");//  '新密码',

        //非空验证
        if (username == null || username.equals("")) {
//            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (oldpassword == null || oldpassword.equals("")) {
//            throw BusinesseLoginException.createException(session, "原密码不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"原密码不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (newpassword == null || newpassword.equals("")) {
//            throw BusinesseLoginException.createException(session, "新密码不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"新密码不能为空");
            ServerResponse.convert2Json(sr,response);
            return;

        }
        if (user == null) {
            throw BusinesseLoginException.createException(session, "当前用户未登录", "3s后跳转到登录页面", "login.jsp");
           /* ServerResponse sr= ServerResponse.createServerResponce(1,"当前用户未登录");
            ServerResponse.convert2Json(sr,response);
            return;*/
        }

        IUserService userService = new UserServiceImpl();
        if (userService.login(user.getUsername(), MD5Utils.GetMD5Code(oldpassword)) != null) {
            userService.updatePassword(session, user.getUsername(), MD5Utils.GetMD5Code(newpassword));
            throw BusinesseLoginException.createException(session, "密码修改成功", "3s后跳转到登录页面", "login.jsp");
//            ServerResponse sr= ServerResponse.createServerResponce(0,"密码修改成功");
//            ServerResponse.convert2Json(sr,response);
//            return;
        } else {
//            throw BusinesseLoginException.createException(session, "原始密码错误", "3s后跳转到登录页面", "login.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"原始密码错误");
            ServerResponse.convert2Json(sr,response);
            return;
        }

    }


    protected void selfInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====selfInfo=======");
        //获取请求参数
        HttpSession session = request.getSession();
        String username = request.getParameter("username");//  '用户名',

        //非空验证
        if (username == null || username.equals("")) {
//            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;

        }

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if (user == null) {
//            throw BusinesseLoginException.createException(session, "获取个人信息失败", "3s后跳转到登录页面", "login.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"获取个人信息失败");
            ServerResponse.convert2Json(sr,response);
            return;
        } else {
            request.getRequestDispatcher("selfinfo.jsp").forward(request, response);
        }

    }


    protected void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====updatePassword");
        //获取请求参数
        HttpSession session = request.getSession();
        String username = request.getParameter("username");//  '用户名',
        String password = request.getParameter("password");//  '密码',
        String token = request.getParameter("token");//  'token',
        //非空验证
        if (username == null || username.equals("")) {
//            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (password == null || password.equals("")) {
//            throw BusinesseLoginException.createException(session, "密码不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"密码不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (token == null || token.equals("")) {
//            throw BusinesseLoginException.createException(session, "token不能为空", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"token不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }

        //调用service
        IUserService userService = new UserServiceImpl();
        int result = userService.updatePassword(session, username, MD5Utils.GetMD5Code(password), token);
        if (result > 0) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
//            throw BusinesseLoginException.createException(session, "修改密码失败", "3s后跳转到输入密码页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"修改密码失败");
            ServerResponse.convert2Json(sr,response);
            return;
        }
//        http://localhost:8080/BusinessWeb/user?operation=5&username=xixi&password=111111&token=7f177737bd1f7c966fc2d48c9d7ea46c

//        http://localhost:8080/BusinessWeb/user?operation=4&username=xixi&question=学校&answer=大学
    }


    protected void checkAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====checkAnswer");
        //获取请求参数
        HttpSession session = request.getSession();
        String username = request.getParameter("username");//  '用户名',
        String question = request.getParameter("question");//  '密码',
        String answer = request.getParameter("answer");//  '答案',
        //非空验证
        if (username == null || username.equals("")) {
//            throw BusinesseLoginException.createException(session, "用户名不能为空", "3s后跳转到回答问题页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (question == null || question.equals("")) {
//            throw BusinesseLoginException.createException(session, "提示问题不能为空", "3s后跳转到回答问题页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"提示问题不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (answer == null || answer.equals("")) {
//            throw BusinesseLoginException.createException(session, "答案不能为空", "3s后跳转到回答问题页面", "answer.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"答案不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }

        //调用service
        IUserService userService = new UserServiceImpl();
        String token = userService.checkAnswer(session, username, question, answer);
        session.setAttribute("forget_token", token);
        request.getRequestDispatcher("newpassword.jsp").forward(request, response);
    }

    protected void findQuestionByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====转入findQuestionByUsername");
        //获取请求参数
        HttpSession httpSession = request.getSession();
        String username = request.getParameter("username");//  '用户名',
        //非空验证
        if (username == null || username.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "用户名不能为空", "3s后跳转到找回密码页面", "findQuestion.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        //调用service层
        IUserService userService = new UserServiceImpl();
        String question = userService.findQuestionByUsername(httpSession, username);
        httpSession.setAttribute("question", question);
        request.getRequestDispatcher("answer.jsp").forward(request, response);


    }


    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=====register");
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

//            throw BusinesseLoginException.createException(request.getSession(), "用户名不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (password == null || password.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "密码不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"密码不能为空");
            ServerResponse.convert2Json(sr,response);
            return;

        }
        if (email == null || email.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "邮箱不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"邮箱不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (phone == null || phone.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "手机号不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"手机号不能为空");
            ServerResponse.convert2Json(sr,response);
            return;

        }
        if (question == null || question.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "找回密码问题不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"找回密码问题不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (answer == null || answer.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "找回密码问题答案不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"找回密码问题答案不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if (role == null || role.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "用户属性不能为空", "3s后跳转到注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户属性不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }

        IUserService userService = new UserServiceImpl();

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(MD5Utils.GetMD5Code(password));
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfo.setQuestion(question);
        userInfo.setAnswer(answer);
        userInfo.setRole(role);

        boolean result = userService.register(httpSession, userInfo);
        if (result) {
            System.out.println("注册成功");
//            throw BusinesseLoginException.createException(httpSession, "注册成功", "3s后跳转到登录页面", "login.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(0,"注册成功");
            ServerResponse.convert2Json(sr,response);
            return;
        } else {

//            throw BusinesseLoginException.createException(httpSession, "注册失败", "3s后跳转回注册页面", "register.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"注册失败");
            ServerResponse.convert2Json(sr,response);
            return;
        }
    }
//localhost:8080/BusinessWeb/user?operation=2&username=xixi&password=123&email=2312@163.com&phone=11111&question=***&answer=1212

    /**
     * 登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=====login");
        //设置响应的格式，不同的servlet的响应格式会不同，无法统一写到过滤器里
        response.setContentType("text/html;charset=utf-8");
        HttpSession httpSession = request.getSession();
        //控制器作用：接收视图层的参数，调用业务逻辑层来处理
        //规定 username=XXX&password=XXX
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //如果为空，都不应该调用业务逻辑层---非空判断
        if (username == null || username.equals("") ) {
//            throw BusinesseLoginException.createException(httpSession, "用户名密码不能为空", "3s后跳转到登录界面", "login.jsp");
            ServerResponse sr= ServerResponse.createServerResponce(1,"用户名不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if(password == null || password.equals("")){
            ServerResponse sr= ServerResponse.createServerResponce(1,"密码不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }


        IUserService userService = new UserServiceImpl();
        UserInfo userInfo = userService.login(username, MD5Utils.GetMD5Code(password));
        //如果查询到信息
        if (userInfo != null) {

            //token
            String macAddress = IpUtils.getMACAddress(IpUtils.getRemoteAddress(request));
//            System.out.println(token);
            String token = MD5Utils.GetMD5Code(macAddress);
            Cookie tokenCookie = new Cookie(Const.TOKENCOOKIE, token);
            tokenCookie.setMaxAge(600);
            response.addCookie(tokenCookie);
            String sql = "update neuedu_user set token = ? where id = ?;";
            boolean updateFlag = DBUtils.updateDB(sql, token, userInfo.getId());
            if (updateFlag) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改不成功");
            }

            //保存登录信息 keep the user login message in session

//            httpSession.setAttribute(Const.CURRENTUSER, userInfo);
//            request.getRequestDispatcher("manage/home.jsp").forward(request, response);

            /*
            *  json格式
            {"id":21,"username":"admin",……}
             */
            StringBuffer buffer = new StringBuffer();
            buffer.append("{");
            buffer.append("\"id\"");
            buffer.append(":");
            buffer.append(userInfo.getId());
            buffer.append(",");
            buffer.append("\"username\"");
            buffer.append(":");
            buffer.append("\"" + userInfo.getUsername() + "\"");
            buffer.append("}");


            Gson gson = new Gson();
            String json = gson.toJson(userInfo);
            UserInfo user = gson.fromJson(json, UserInfo.class);
            //dataType为json时允许跨域的设置
//            response.setHeader("Access-Control-Allow-Origin","*");


// jsonp时允许跨域方式对应的请求和响应格式
// http://localhost:8080/BusinessWeb/user?callback=jQuery33107844840649325525_1536305994785&operation=1&username=admin3&password=admin3&_=1536305994786
//  jQuery331015067187381574076_1536306056397({"id":26,"username":"admin3","password":"13122211212","email":"admin3@163.com","question":"xxx","answer":"xxx","role":1})
            String call = request.getParameter("callback");//jsonp方式设置允许跨域


            PrintWriter pwriter = response.getWriter();
//            pwriter.write(buffer.toString());
//            pwriter.write(json);
            pwriter.write(call + "(" + json + ")");
        } else {//用户名或密码输入错误 username or password error
            ServerResponse sr = ServerResponse.createServerResponce(1,"用户名密码输入错误");
            ServerResponse.convert2Json(sr,response);
            return ;
        }

    }
}
