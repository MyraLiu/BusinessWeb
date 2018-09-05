package com.neuedu.dao;

import com.neuedu.pojo.UserInfo;

public interface IUserDao {
    public UserInfo login(String username, String password);
    public UserInfo autoLogin(String token);
    public UserInfo selectById(int id);
    public int checkUsername(String username);
    public int checkEmail(String email);
    public  boolean register(UserInfo user);
    public UserInfo selectByUsername(String username);
    public UserInfo selectByemail(String email);
    public String findQuestionByUsername(String username);
    public String findQuestionByEmail(String email);

}
