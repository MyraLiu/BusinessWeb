package com.neuedu.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {


    /**
     * 对数据库进行增删改查的方法
     * @param sql 修改语句
     * @param objects 可变参数用于存放占位符对应数据
     * @return 返回修改是否成功
     */
    public static boolean updateDB(String sql,Object...objects){
        int i = -1;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = JDBCUtils.getConnection();
            pstm=connection.prepareStatement(sql);
            for (int j=0;j<objects.length;j++) {
                pstm.setObject(j+1,objects[j]);
            }
             i = pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(connection,pstm);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return i>0;
    }
}
