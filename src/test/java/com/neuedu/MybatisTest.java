package com.neuedu;

import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoMyBatisImpl;
import com.neuedu.pojo.UserInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MybatisTest {
    IUserDao userDao = new UserDaoMyBatisImpl();

@Test
    public  void testLogin(){


        UserInfo user = userDao.login("admin3","32cacb2f994f6b42183a1300d9a3e8d6");
        System.out.println(user);

    }
    @Test
    public void testRegister(){
    UserInfo user = new UserInfo();
    user.setUsername("hipo");
    user.setPassword("123456");
    user.setEmail("hipo@qq.com");
    user.setPhone("109876543211");
    user.setQuestion("xxx");
    user.setAnswer("xxxx");
    user.setRole(1);

        System.out.println(userDao.register(user));

    }

@Test
    public void testCheckUsername(){
        System.out.println(userDao.checkUsername("admin4"));
    }
    @Test
    public  void  testFindall(){
        System.out.println(userDao.findAll());
    }
@Test
    public void testFindallByUsername(){
        System.out.println(userDao.findAllByUsername("admin3"));
       System.out.println(userDao.findAllByUsername(null));
    }


    @Test
public void testFindByOption(){
    UserInfo user = new UserInfo();
//    有顺序
//    user.setUsername("admin3");
    user.setId(23);
    user.setEmail("admin@193.com");
    System.out.println(userDao.findByOption(user));
}

    @Test
public void testFindByIds(){
    List<Integer> ids = new ArrayList<>();
    ids.add(21);
    ids.add(23);
    System.out.println(userDao.findByIds(ids));
}

    @Test
    public void testUpdateUser(){
        UserInfo user = new UserInfo();
//    有顺序
        user.setId(23);
        user.setEmail("qqqq@193.com");
        System.out.println(userDao.updateUser(user));
}
}
