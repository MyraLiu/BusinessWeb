package com.neuedu.common;

import org.omg.CORBA.NO_PERMISSION;

public enum ResponseCode {

    SUCCESS(0,"成功"),
    NEED_LOGIN(1,"需要登录"),
    NO_PERMISSION(2,"无权限"),
    GETSUBCATEGORY_NEED_CATEGORYID(3,"categoryid必须"),
    NEED_PRODUCT(5,"商品参数必传"),
    NEED_PRODUCT_STATUS(6,"商品参数必须"),
    FAIL(100,"失败");


    private Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ResponseCode() {
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
