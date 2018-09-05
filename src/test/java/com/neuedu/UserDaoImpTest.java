package com.neuedu;

import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.pojo.UserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImpTest {

IUserDao userDao ;
@Before
   public void before(){
       userDao = new UserDaoImpl();
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


    @After
    public void destory(){
    userDao = null;
    }
}
