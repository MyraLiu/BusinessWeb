package com.neuedu.common;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerResponse<T> {

    //属性
    private int status ;// 状态码
    private T data;//传输的数据 可能是各种类型
    private String msg;// 异常信息

    //私有化构造方法
    private ServerResponse() {
    }

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //提供新建对象的方法
    public static <T>ServerResponse<T> createServerResponce(int status){
        return new ServerResponse<T>(status);
    }

    public static <T>ServerResponse<T> createServerResponce(int status,String msg){
        return new ServerResponse<T>(status,msg);
    }



    public static <T>ServerResponse<T> createServerResponce(int status,T data){
        return new ServerResponse<T>(status,data);
    }

    public static <T>ServerResponse<T> createServerResponce(int status,T data,String msg){
        return new ServerResponse<T>(status,data,msg);
    }
/**
 * 将serverresponse转化为json格式
* */
    public static void convert2Json(ServerResponse sr, HttpServletResponse response){

        Gson gson = new Gson();

        String json = gson.toJson(sr);
        try{
            PrintWriter pw = response.getWriter();
           pw.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
