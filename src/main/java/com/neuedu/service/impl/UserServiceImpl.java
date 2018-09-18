package com.neuedu.service.impl;

import com.mysql.fabric.Server;
import com.neuedu.businessconst.Const;
import com.neuedu.cache.TokenCache;
import com.neuedu.common.MD5Utils;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.exception.BusinesseLoginException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;


    public  int checkUsername(String username){
        return userDao.checkUsername(username);
    }

    /**
     * 登录的业务逻辑
     * 根据用户名和密码查询是否有该用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<UserInfo> login(String username, String password) {

       UserInfo user = userDao.login(username,MD5Utils.GetMD5Code(password));
       if(user!=null){
           return ServerResponse.createServerResponce(0,user);
       }else{
           return ServerResponse.createServerResponce(1,user,"登录失败");
       }

    }

    @Override
    public UserInfo autoLogin(String token) {
        return userDao.autoLogin(token);
    }


    @Override
    public ServerResponse<UserInfo> register(UserInfo userInfo) {
        // 判断用户名是否存在
        int result_username = userDao.checkUsername(userInfo.getUsername());
        if (result_username > 0) {
            return ServerResponse.createServerResponce(7,"用户名已经存在");
        }
        //判断邮箱是否存在
        int result_email = userDao.checkEmail(userInfo.getEmail());
        if (result_email > 0) {
            return ServerResponse.createServerResponce(7,"邮箱已经存在");
        }
        //密码加密
        userInfo.setPassword(MD5Utils.GetMD5Code(userInfo.getPassword()));
        //设置角色
        userInfo.setRole(1);
        //注册

        boolean result =userDao.register(userInfo);
        if(result){
            return ServerResponse.createServerResponce(0,"注册成功");
        }else{
            return ServerResponse.createServerResponce(8,"注册失败");
        }
    }

    @Override
    public ServerResponse<String> findQuestionByUsername(HttpSession session, String str) {
        //验证用户名是否存在
        int result = userDao.checkUsername(str);
        if (result <= 0 ) {
        return ServerResponse.createServerResponce(2,"用户名或邮箱不存在");
        }
        String question = userDao.findQuestionByUsername(str);
        return ServerResponse.createServerResponce(0,question,"查找到忘记密码问题");
    }

    @Override
    public  ServerResponse<String> checkAnswer(String username, String question, String answer) {

        //校验 username是否存在
        int result = userDao.checkUsername(username);
        if (result <= 0) {
        return ServerResponse.createServerResponce(4,"用户名不存在");
        }

        //校验答案是否正确
        int count = userDao.checkAnswer(username, question, answer);
        if (count > 0) {//校验成功
            //防止横向越权
            String forget_token = UUID.randomUUID().toString();
            //缓存-token存放到缓存中
            TokenCache.set(Const.TOKEN_PREFIX + username, forget_token);
            return ServerResponse.createServerResponce(0,forget_token," ");

        } else {
            return ServerResponse.createServerResponce(5,"答案不正确");
        }
    }

    @Override
    public ServerResponse<String> updatePassword( String username, String newPassword, String token) {
        // 非空判断
        int result_username = userDao.checkUsername(username);
        if (result_username <= 0) {
            return ServerResponse.createServerResponce(4,"用户名不存在");
        }

        //判断token是否有效
        String cacheToken = TokenCache.get(Const.TOKEN_PREFIX + username);
        if (!cacheToken.equals(token)) {
            return ServerResponse.createServerResponce(5,"无效token");
        }

        //修改密码

       int result =  userDao.updatepassword(username, newPassword);
        if(result>0){
            return ServerResponse.createServerResponce(0,"修改密码成功");
        }else{
            return ServerResponse.createServerResponce(6,"修改密码失败");
        }
    }

    //TODO:
    @Override
    public  ServerResponse<UserInfo> updatePassword(String oldPassword, String  newPassword,UserInfo user){
        // 获取用户信息，验证旧密码是否正确
        UserInfo oldUser = userDao.login(user.getUsername(), MD5Utils.GetMD5Code(oldPassword));
        if (oldUser == null) {
            return ServerResponse.createServerResponce(6,"旧密码错误");
        }
        int result = userDao.updatepassword(user.getUsername(),newPassword);
        if(result>0){
            return ServerResponse.createServerResponce(0,"密码修改成功");
        }else{
            return ServerResponse.createServerResponce(7,"密码修改失败");
        }
    }


    public int updateTokenById(int userid, String token) {

        return userDao.updateTokenByID(userid, token);
    }

    @Override
    public boolean isAdminRole(UserInfo user) {
        return user.getRole() == 0;
    }

}
