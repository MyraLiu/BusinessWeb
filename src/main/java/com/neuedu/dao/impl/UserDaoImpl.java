package com.neuedu.dao.impl;

import com.neuedu.common.JDBCUtils;
import com.neuedu.dao.IUserDao;
import com.neuedu.pojo.UserInfo;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements IUserDao {


    @Override
    public UserInfo login(String username, String password) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select * from neuedu_user where username=? and password=?;";
           preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
              int id=  resultSet.getInt(1);
               String _username =  resultSet.getString(2);
               String _password = resultSet.getString(3);
               String email = resultSet.getString(4); ;//  用户邮箱
               String phone =  resultSet.getString(5);;//  用户电话
               String question =  resultSet.getString(6);;//   '找回密码问题',
               String answer =  resultSet.getString(7);;//   '找回密码答案',
               int role = resultSet.getInt(8);//   '角色0-管理员，1-普通用户',

                UserInfo user = new UserInfo();
                user.setId(id);
                user.setUsername(_username);
                user.setPassword(_password);
                user.setEmail(email);
                user.setPassword(phone);
                user.setQuestion(question);
                user.setAnswer(answer);
                user.setRole(role);
                return user;
            }
        }catch (SQLException e){

            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public UserInfo autoLogin(String token) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select * from neuedu_user where token=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,token);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
                int id=  resultSet.getInt(1);
                String _username =  resultSet.getString(2);
                String _password = resultSet.getString(3);
                String email = resultSet.getString(4); ;//  用户邮箱
                String phone =  resultSet.getString(5);;//  用户电话
                String question =  resultSet.getString(6);;//   '找回密码问题',
                String answer =  resultSet.getString(7);;//   '找回密码答案',
                int role = resultSet.getInt(8);//   '角色0-管理员，1-普通用户',

                UserInfo user = new UserInfo();
                user.setId(id);
                user.setUsername(_username);
                user.setPassword(_password);
                user.setEmail(email);
                user.setPassword(phone);
                user.setQuestion(question);
                user.setAnswer(answer);
                user.setRole(role);
                return user;
            }
        }catch (SQLException e){

            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
