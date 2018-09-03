package com.neuedu.service;

import com.neuedu.pojo.UserInfo;

public interface IUserService {
    /**
     * 登录
     */
    UserInfo login(String username ,String Password);
    UserInfo autoLogin(String token);
}
