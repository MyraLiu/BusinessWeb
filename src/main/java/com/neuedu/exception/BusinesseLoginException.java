package com.neuedu.exception;

import com.neuedu.businessconst.Const;

import javax.servlet.http.HttpSession;

/**
 * 自定义的异常类，继承RuntimeException
 */
public class BusinesseLoginException extends  RuntimeException {
    //异常类的信息
    private String msg;
    //异常提示
    private String warn;
    //跳转的页面url
    private String url;

    public BusinesseLoginException() {
    }

    private BusinesseLoginException(String msg, String warn, String url) {
        this.msg = msg;
        this.warn = warn;
        this.url = url;
    }

    @Override
    public String toString() {
        return "BusinesseLoginException{" +
                "msg='" + msg + '\'' +
                ", warn='" + warn + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public String getWarn() {
        return warn;
    }

    public String getUrl() {
        return url;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static BusinesseLoginException createException(HttpSession session,String msg,String warn,String url){
        BusinesseLoginException businesseLoginException = new BusinesseLoginException(msg,warn,url);
        session.setAttribute(Const.EXCEPTION,businesseLoginException);
        return businesseLoginException;
    }
}
