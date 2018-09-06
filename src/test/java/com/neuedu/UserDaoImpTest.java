package com.neuedu;

import com.neuedu.cache.TokenCache;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImpTest {

IUserDao userDao ;
IUserService userService;
@Before
   public void before(){

    userDao = new UserDaoImpl();
    userService = new UserServiceImpl();
   }

    @Test
    public void testLogin(){

    UserInfo userInfo = userDao.login("admin","123456");
        System.out.println(userInfo);
    }
    @Test
    public void testCheckUsername(){
    int i=userDao.checkUsername("bili");
    System.out.println(i);
    }
    @Test
    public void testFindQuestionByUsername(){
        System.out.println(userDao.findQuestionByUsername("xixi"));
    }
    @Test
    public void testCheckAnswer(){
        System.out.println(userDao.checkAnswer("xixi","学校","大学"));
    }
    @Test
    public void testTokenCache(){
        TokenCache.set("hello","what");
        System.out.println(TokenCache.get("token_xixi"));
    }

    @Test
    public void testupdatePassword(){
        System.out.println(userDao.updatepassword("xixi","111111"));
    }


    @After
    public void destory(){
    userDao = null;
    }
}
