package com.neuedu.controller;

import com.google.gson.Gson;
import com.neuedu.businessconst.Const;
import com.neuedu.common.*;
import com.neuedu.exception.BusinesseLoginException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/logout")
    @ResponseBody
    protected ServerResponse<String> logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====exit=======");
        //获取请求参数
        HttpSession session = request.getSession();


        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (user != null) {
            session.removeAttribute(Const.CURRENTUSER);
            //清除用户的token
            userService.updateTokenById(user.getId(), "");
        }
        return ServerResponse.createServerResponce(0, "成功");


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

    /**
     * 登录状态下重置密码
     *
     * @param oldpassword
     * @param newpassword
     * @return
     */
    @RequestMapping("/setpassword")
    protected ServerResponse<UserInfo> updatePasswordInLogin(@RequestParam("oldpassword") String oldpassword,
                                                             @RequestParam("newpassword") String newpassword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //非空判断
        if (oldpassword == null || oldpassword.equals("")) {
            return ServerResponse.createServerResponce(1, "原密码不能为空");
        }
        if (newpassword == null || newpassword.equals("")) {
            return ServerResponse.createServerResponce(1, "新密码不能为空");
        }
        //判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (user == null) {
            return ServerResponse.createServerResponce(5, "未登录或登录已过期");
        }
        return userService.updatePassword(oldpassword, MD5Utils.GetMD5Code(newpassword), user);
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/userinfo")
    @ResponseBody
    protected ServerResponse<UserInfo> getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====getUserInfo=======");
        //获取请求参数
        HttpSession session = request.getSession();
        String username = request.getParameter("username");//  '用户名',

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if (user == null) {
            return ServerResponse.createServerResponce(1, "登录过期或未登录");
        }
        return ServerResponse.createServerResponce(0, user, "成功");
    }

    /**
     * 忘记密码-重置密码

     * @throws ServletException
     * @throws IOException
     */
@RequestMapping("/updatepassword")
    protected ServerResponse<String> updatePassword(String username,String newpassword,String token) {

        //非空验证
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponce(1, "用户名不能为空");
        }
        if (newpassword == null || newpassword.equals("")) {
            return ServerResponse.createServerResponce(2, "密码不能为空");
        }
        if (token == null || token.equals("")) {
            return ServerResponse.createServerResponce(3, "token不能为空");
        }

        //调用service
       return userService.updatePassword(username, MD5Utils.GetMD5Code(newpassword), token);
    }

@RequestMapping("/checkanswer")
    protected ServerResponse<String> checkAnswer(String username,String question,String answer,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====checkAnswer");

        //非空验证
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponce(1, "用户名不能为空");
        }
        if (question == null || question.equals("")) {
            return ServerResponse.createServerResponce(2, "提示问题不能为空");
        }
        if (answer == null || answer.equals("")) {
            return ServerResponse.createServerResponce(3, "答案不能为空");
        }
        //调用service
      return userService.checkAnswer(username,question,answer);
    }

    @RequestMapping("/findquestion")
    protected ServerResponse<String> findQuestionByUsername(String username, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=====转入findQuestionByUsername");
        //获取请求参数
        HttpSession httpSession = request.getSession();
        //非空验证
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponce(1, "用户名不能为空");
        }
        //调用service层
       return userService.findQuestionByUsername(httpSession, username);

    }


    /**
     * 注册
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/register")
    protected ServerResponse<UserInfo> register(String username, String password, String email, String phone, String question, String answer) throws ServletException, IOException {

        if (username == null || username.equals("")) {

//            throw BusinesseLoginException.createException(request.getSession(), "用户名不能为空", "3s后跳转到注册页面", "register.jsp");
            return ServerResponse.createServerResponce(1, "用户名不能为空");
        }
        if (password == null || password.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "密码不能为空", "3s后跳转到注册页面", "register.jsp");
            return ServerResponse.createServerResponce(2, "密码不能为空");

        }
        if (email == null || email.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "邮箱不能为空", "3s后跳转到注册页面", "register.jsp");
            return ServerResponse.createServerResponce(3, "邮箱不能为空");
        }
        if (phone == null || phone.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "手机号不能为空", "3s后跳转到注册页面", "register.jsp");
            return ServerResponse.createServerResponce(4, "手机号不能为空");

        }
        if (question == null || question.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "找回密码问题不能为空", "3s后跳转到注册页面", "register.jsp");
            return ServerResponse.createServerResponce(5, "找回密码问题不能为空");
        }
        if (answer == null || answer.equals("")) {
//            throw BusinesseLoginException.createException(request.getSession(), "找回密码问题答案不能为空", "3s后跳转到注册页面", "register.jsp");
            return ServerResponse.createServerResponce(6, "找回密码问题答案不能为空");
        }


        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfo.setQuestion(question);
        userInfo.setAnswer(answer);
        userInfo.setRole(1);

        return userService.register(userInfo);
    }
//localhost:8080/BusinessWeb/user?operation=2&username=xixi&password=123&email=2312@163.com&phone=11111&question=***&answer=1212


    @RequestMapping("/login")
    protected ServerResponse<UserInfo> login(String username, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SocketException, UnknownHostException {

        //如果为空，都不应该调用业务逻辑层---非空判断
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponce(2, "用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServerResponse.createServerResponce(3, "密码不能为空");
        }

        if (userService.checkUsername(username) <= 0) {
            return ServerResponse.createServerResponce(4, "用户名不存在");
        }

        ServerResponse<UserInfo> serverResponse = userService.login(username, password);
        UserInfo user = serverResponse.getData();
        //如果查询到信息
        if (user != null) {

            //token
            String ip = IpUtils.getRemoteAddress(request);
            String mac = IpUtils.getMACAddress(ip);
            String token = MD5Utils.GetMD5Code(mac);
            Cookie tokenCookie = new Cookie(Const.TOKENCOOKIE, token);
            tokenCookie.setMaxAge(7 * 24 * 3600);
            response.addCookie(tokenCookie);
            String sql = "update neuedu_user set token = ? where id = ?;";
            System.out.println(sql);
            boolean updateFlag = DBUtils.updateDB(sql, token, user.getId());

            session.setAttribute(Const.CURRENTUSER, user);
            return serverResponse;
        } else {//用户名或密码输入错误 username or password error
            return ServerResponse.createServerResponce(1, "用户名密码输入错误");

        }

    }
}
