package com.neuedu.service;

import com.neuedu.pojo.UserInfo;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

public interface IUserService {
    /**
     * 登录
     */
    UserInfo login(String username ,String Password);

    /**
     * 利用token的自动登录
     * @param token
     * @return
     */
    UserInfo autoLogin(String token);

    /**
     * 注册
     * @param session
     * @param userInfo
     * @return
     */
    boolean register(HttpSession session, UserInfo userInfo);

    /**
     * 根据用户名查找找回密码问题
     * @param session
     * @param username
     * @return
     */
    String findQuestionByUsername(HttpSession session,String username);

    /**
     * 校验问题及答案
     * @param session
     * @param username
     * @param question
     * @param answer
     * @return
     */
    String  checkAnswer(HttpSession session,String username,String question,String answer);

    /**
     * 修改密码
     * @param session
     * @param username
     * @param newPassword
     * @param token
     * @return
     */
    int updatePassword(HttpSession session,String username,String  newPassword,String token);


    int updatePassword(HttpSession session,String username,String  newPassword);

    int updateTokenById(int userid,String token);

    /**
     * 检验用户权限
     * 单一职责
     * @return
     */
    boolean isAdminRole(UserInfo user );

}
