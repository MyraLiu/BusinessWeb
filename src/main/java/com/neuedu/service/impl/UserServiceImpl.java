package com.neuedu.service.impl;

import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;

public class UserServiceImpl implements IUserService{

    IUserDao userDao = new UserDaoImpl();



    /**
     * 登录的业务逻辑
     * 根据用户名和密码查询是否有该用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserInfo login(String username, String password) {

        return userDao.login(username,password);
    }

    @Override
    public UserInfo autoLogin(String token) {
        return userDao.autoLogin(token);
    }
}
