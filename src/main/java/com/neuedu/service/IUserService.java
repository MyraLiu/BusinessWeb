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

    boolean register(HttpSession session, UserInfo userInfo);
    boolean confirmAccount(HttpSession session,String str);
}
