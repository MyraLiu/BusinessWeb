package com.neuedu.dao;

import com.neuedu.pojo.UserInfo;

public interface IUserDao {
    public UserInfo login(String username, String password);
    public UserInfo autoLogin(String token);
}
