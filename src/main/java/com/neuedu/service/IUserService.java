package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

public interface IUserService {
   int checkUsername(String username);

    /**
     * 登录
     */
    ServerResponse<UserInfo> login(String username ,String Password);

    /**
     * 利用token的自动登录
     * @param token
     * @return
     */
    UserInfo autoLogin(String token);

    /**
     * 注册
     * @param userInfo
     * @return
     */
    ServerResponse<UserInfo> register(UserInfo userInfo);

    /**
     * 根据用户名查找找回密码问题
     * @param session
     * @param username
     * @return
     */
    ServerResponse<String> findQuestionByUsername(HttpSession session,String username);

    /**
     * 校验问题及答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String>  checkAnswer(String username,String question,String answer);


    ServerResponse<String> updatePassword(String username,String  newPassword,String token);


     ServerResponse<UserInfo> updatePassword(String oldPassword, String  newPassword,UserInfo user);

    int updateTokenById(int userid,String token);

    /**
     * 检验用户权限
     * 单一职责
     * @return
     */
    boolean isAdminRole(UserInfo user );

}
