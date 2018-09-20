package com.neuedu.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

   private static Properties p= new Properties();
    static {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //0919-02-24:44
    public static String getProperty(String key){
        return p.getProperty(key);
    }
}
