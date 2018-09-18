package com.neuedu.dao.impl;

import com.neuedu.common.MyBatisUtils;
import com.neuedu.dao.IUserDao;
import com.neuedu.pojo.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class UserDaoMyBatisImpl implements IUserDao{

    @Autowired
    private  SqlSession sqlSession ;
    @Override
    public UserInfo login(String username, String password) {
//        String configfile = "mybatis-config.xml";
//        Reader reader = null;
//        SqlSessionFactory sqlSessionFactory = null;
//        SqlSession sqlSession=null;
//        try {
//            reader= Resources.getResourceAsReader(configfile);
//            sqlSessionFactory  = new SqlSessionFactoryBuilder().build(reader);
//            sqlSession = sqlSessionFactory.openSession();
//            Map<String,String> map= new HashMap<>();
//            map.put("username",username);
//            map.put("password",password);
//            UserInfo user = sqlSession.selectOne("com.neuedu.dao.IUserDao.login",map);
//            return user;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (sqlSession!=null)
//            {sqlSession.close();
//            }
//        }
//简单封装

        Map<String,String> map= new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            UserInfo user = sqlSession.selectOne("com.neuedu.dao.IUserDao.login",map);
            return user;


    }

    @Override
    public UserInfo autoLogin(String token) {
        return null;
    }

    @Override
    public UserInfo selectById(int id) {
        return null;
    }

    @Override
    public int checkUsername(String username) {
        int result = sqlSession.selectOne("com.neuedu.dao.IUserDao.checkUsername",username);
        return result;
    }

    @Override
    public int checkEmail(String email) {
        return 0;
    }

    @Override
    public boolean register(UserInfo user) {

        int i=sqlSession.insert("com.neuedu.dao.IUserDao.register",user);
        sqlSession.commit();//增删改需要手动提交事务
        sqlSession.close();
        return i>0;
    }

    @Override
    public UserInfo selectByUsername(String username) {
        return null;
    }

    @Override
    public UserInfo selectByemail(String email) {
        return null;
    }

    @Override
    public String findQuestionByUsername(String username) {
        return null;
    }

    @Override
    public String findQuestionByEmail(String email) {
        return null;
    }

    @Override
    public int checkAnswer(String username, String question, String answer) {
        return 0;
    }

    @Override
    public int updatepassword(String username, String newPassword) {
        return 0;
    }

    @Override
    public int updateTokenByID(int userid, String token) {
        return 0;
    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> listu =sqlSession.selectList("com.neuedu.dao.IUserDao.findAll");
        return listu;
    }

    @Override
    public List<UserInfo> findAllByUsername(String username) {
        List<UserInfo> listu =sqlSession.selectList("com.neuedu.dao.IUserDao.findAllByUsername",username);
        return listu;
    }

    @Override
    public UserInfo findByOption(UserInfo user) {

        return sqlSession.selectOne("com.neuedu.dao.IUserDao.findByOption",user);
    }

    @Override
    public List<UserInfo> findByIds(List<Integer> listid) {
        return sqlSession.selectList("com.neuedu.dao.IUserDao.findByIds",listid);
    }

    @Override
    public int updateUser(UserInfo user) {
        int result = sqlSession.update("com.neuedu.dao.IUserDao.updateUser",user);

        sqlSession.commit();

        return result;
    }

    @Override
    public int insertUsers(List<UserInfo> users) {
        return 0;
    }
}
