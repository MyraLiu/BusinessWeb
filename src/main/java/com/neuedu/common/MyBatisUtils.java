package com.neuedu.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {


    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        Reader reader;
        String configFile ="mybatis-config.xml";
        try {
            reader = Resources.getResourceAsReader(configFile);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

    public  static void close(SqlSession s){
        if(s!=null){
            s.close();
        }
    }

}
