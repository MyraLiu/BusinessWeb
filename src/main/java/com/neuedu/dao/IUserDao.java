package com.neuedu.dao;

import com.neuedu.pojo.UserInfo;

import java.util.List;

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
    public int checkAnswer(String username,String question,String answer);
    public  int updatepassword(String username,String newPassword);
    public int updateTokenByID(int  userid,String token);
    public List<UserInfo> findAll();
    List<UserInfo> findAllByUsername(String username);
    //先按照用户名查询，如果不存在按照id查询，如果id不存在，按照email查询
    public UserInfo findByOption(UserInfo user);

    public List<UserInfo> findByIds(List<Integer> listid);
    public int updateUser(UserInfo user);
    public int insertUsers(List<UserInfo> users);


}
