package com.neuedu.service.impl;

import com.neuedu.businessconst.Const;
import com.neuedu.cache.TokenCache;
import com.neuedu.common.MD5Utils;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.exception.BusinesseLoginException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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

        return userDao.login(username, password);
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
        userInfo.setPassword(userInfo.getPassword());
        //设置角色
        userInfo.setRole(1);
        //注册

        return userDao.register(userInfo);
    }

    @Override
    public String findQuestionByUsername(HttpSession session,String str) {
        //验证用户名是否存在
      if(userDao.checkUsername(str)<=0&&userDao.checkEmail(str)<=0){
          throw BusinesseLoginException.createException(session,"用户名不存在","3s后跳转到修改密码页面","findPassword.jsp");
      }


        return userDao.findQuestionByUsername(str);
    }

    @Override
    public String checkAnswer(HttpSession session, String username, String question, String answer) {

       //校验 username是否存在
        int result = userDao.checkUsername(username);
        if(result<=0){
            throw BusinesseLoginException.createException(session,"用户名不存在","3s后跳转到回答问题页面","answer.jsp");
        }

        //校验答案是否正确
        int count = userDao.checkAnswer(username,question,answer);
        if(count>0){//校验成功
            //防止横向越权
            String uuids = UUID.randomUUID().toString();
            String forget_token = MD5Utils.GetMD5Code(uuids);


            //缓存-token存放到缓存中
            TokenCache.set(Const.TOKEN_PREFIX+username,forget_token);
            return forget_token;

        }else{
            throw BusinesseLoginException.createException(session,"答案校验不正确","3s后跳转到回答问题页面","answer.jsp");
        }
    }

    @Override
    public int updatePassword(HttpSession session,String username, String newPassword, String token) {
        // 非空判断
        int result_username = userDao.checkUsername(username);
        if(result_username<=0){
            throw BusinesseLoginException.createException(session,"用户名不存在","3s后跳转到修改密码页面","newpassword.jsp");
        }

        //判断token是否有效
        String cacheToken = TokenCache.get(Const.TOKEN_PREFIX+username);
        if(!cacheToken.equals(token)){
            throw BusinesseLoginException.createException(session,"无效token","3s后跳转到修改密码页面","newpassword.jsp");
        }

        //修改密码

        return userDao.updatepassword(username,newPassword) ;
    }

    @Override
    public int updatePassword(HttpSession session,String username, String newPassword) {
        // 非空判断
        int result_username = userDao.checkUsername(username);
        if(result_username<=0){
            throw BusinesseLoginException.createException(session,"用户名不存在","3s后跳转到修改密码页面","newpassword.jsp");
        }


        return userDao.updatepassword(username,newPassword);
    }


    public int updateTokenById(int userid,String token){

        return userDao.updateTokenByID(userid,token);
    }

}
