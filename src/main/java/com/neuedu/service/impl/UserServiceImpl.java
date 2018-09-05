package com.neuedu.service.impl;

import com.neuedu.businessconst.Const;
import com.neuedu.common.MD5Utils;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.exception.BusinesseLoginException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;

import javax.servlet.http.HttpSession;

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




    @Override
    public boolean register(HttpSession session,UserInfo userInfo) {
        // 判断用户名是否存在
        int result_username = userDao.checkUsername(userInfo.getUsername());
        if(result_username>0){
            throw BusinesseLoginException.createException(session,"用户名已经存在","3s后跳转到注册页面","register.jsp");
        }
        //判断邮箱是否存在
        int result_email = userDao.checkUsername(userInfo.getEmail());
        if(result_email>0){
            throw BusinesseLoginException.createException(session,"用户名已经存在","3s后跳转到注册页面","register.jsp");
        }
        //密码加密
        userInfo.setPassword(MD5Utils.GetMD5Code(userInfo.getPassword()));
        //设置角色
        userInfo.setRole(1);
        //注册

        return userDao.register(userInfo);
    }

    @Override
    public boolean confirmAccount(HttpSession session,String str) {
        //验证用户名是否存在
        UserInfo userInfo = userDao.selectByUsername(str);
        if(userInfo == null ){
            userInfo= userDao.selectByemail(str);
        }
        if(userInfo == null){
            throw BusinesseLoginException.createException(session,"用户名邮箱不存在","3s后返回登录界面","login.jsp");
        }
        session.setAttribute(Const.CURRENTUSER,userInfo);


        return false;
    }


}
