package com.neuedu.common;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    //类变量，配置文件
      private static Properties properties = new Properties();

      //静态代码块，类加载时加载
    static {
        InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        try {
            //通过输入流加载配置文件
            properties.load(inputStream);
            //加载jdbc-mysql驱动
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("jdbc.url"),properties.getProperty("jdbc.user"),properties.getProperty("jdbc.password"));
    }


    public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
    }


    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }

        if(resultSet!=null){
            resultSet.close();
        }
    }
}
