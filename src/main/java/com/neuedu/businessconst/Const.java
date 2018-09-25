package com.neuedu.businessconst;

public class Const {
    public static final String CURRENTUSER = "user";
    public static final String USERNAMECOOKIE = "username";
    public static final String PASSWORDCOOKIE = "password";
    public static final String TOKENCOOKIE = "auto_login_token";
    public  static  final  String EXCEPTION = "ex";
    public static final String  TOKEN_PREFIX = "token_";


    public enum ProductCode{

        ILLEGAL_PARAM(101,"参数错误");


        private  int code;
        private  String msg;


        private  ProductCode(int code ,String msg){
        this.code=code;
        this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


    public enum STOCK{
        LIMIT_NUM_SUCCESS("LIMIT_NUM_SUCCESS"),
        LIMIT_NUM_FAIL("LIMIT_NUM_FAIL");

        private String stockdesc;
        private STOCK(String stockdesc){
            this.stockdesc = stockdesc;
        }

        public String getStockdesc(){
            return stockdesc;
        }

        public void setStockdesc(String stockdesc){
            this.stockdesc = stockdesc;
        }
    }
}
