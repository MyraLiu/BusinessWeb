package com.neuedu.dao.impl;

import com.neuedu.common.DBUtils;
import com.neuedu.common.JDBCUtils;
import com.neuedu.dao.IUserDao;
import com.neuedu.pojo.UserInfo;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
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

    @Override
    public UserInfo selectById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select * from neuedu_user where id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
                int _id=  resultSet.getInt(1);
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
    public int checkUsername(String username)  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select count(1) from neuedu_user where username=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int count = resultSet.getInt(1);
                return count;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

        @Override
    public int checkEmail(String email) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
                String sql = "select count(1) from neuedu_user where email=?;";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.first()) {
                    int count = resultSet.getInt(1);
                    return count;
                }
                return 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    JDBCUtils.close(connection,preparedStatement,resultSet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
    }

    @Override
    public boolean register(UserInfo user) {
        String sql = "insert into neuedu_user " +
                            "(username,password,email,phone,question,answer,role,create_time,update_time)" +
                            " values (?,?,?,?,?,?,?,now(),now());";

        return  DBUtils.updateDB(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getQuestion(),user.getAnswer(),user.getRole());


    }

    @Override
    public UserInfo selectByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select * from neuedu_user where username=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
                int id=  resultSet.getInt(1);
                String _username =  resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4); ;//  用户邮箱
                String phone =  resultSet.getString(5);;//  用户电话
                String question =  resultSet.getString(6);;//   '找回密码问题',
                String answer =  resultSet.getString(7);;//   '找回密码答案',
                int role = resultSet.getInt(8);//   '角色0-管理员，1-普通用户',

                UserInfo user = new UserInfo();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
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
    public UserInfo selectByemail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select * from neuedu_user where email=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.first()){
                int id=  resultSet.getInt(1);
                String _username =  resultSet.getString(2);
                String _password = resultSet.getString(3);
                String _email = resultSet.getString(4); ;//  用户邮箱
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
    public String findQuestionByUsername(String username) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select question from neuedu_user where username=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return  resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    @Override
    public String findQuestionByEmail(String email) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select question from neuedu_user where email=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return  resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public int checkAnswer(String username, String question, String answer) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "select count(1) from neuedu_user where username=? and question=? and answer=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int count = resultSet.getInt(1);
                return count;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int updatepassword(String username, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "update neuedu_user set password=? where username = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            return preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    public int updateTokenByID(int  userid,String token){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//            System.out.println("connection = " + connection);
            String sql = "update neuedu_user set token=? where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, userid);
            return preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JDBCUtils.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public List<UserInfo> findAll() {
        return null;
    }



    public List<UserInfo> findAllByUsername(String username){
        return null;
    }

    @Override
    public UserInfo findByOption(UserInfo user) {
        return null;
    }

    @Override
    public List<UserInfo> findByIds(List<Integer> listid) {
        return null;
    }

    @Override
    public int updateUser(UserInfo user) {
        return 0;
    }

    @Override
    public int insertUsers(List<UserInfo> users) {
        return 0;
    }
}
