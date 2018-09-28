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




    public enum ALIPAYSTATUS{

//  10      WAIT_BUYER_PAY	交易创建，等待买家付款
//  60      TRADE_CLOSED	未付款交易超时关闭，或支付完成后全额退款
//  20      TRADE_SUCCESS	交易支付成功
//  50      TRADE_FINISHED	交易结束，不可退款

        WAIT_BUYER_PAY(10,"WAIT_BUYER_PAY"),
        TRADE_CLOSED(60,"TRADE_CLOSED"),
        TRADE_SUCCESS(20,"TRADE_SUCCESS"),
        TRADE_FINISHED(50,"TRADE_FINISHED");


        private int code;
        private String msg;

        ALIPAYSTATUS(int code,String msg){
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


    public enum PAYPLATFORM{
        ALIPAY(1,"alipay"),
        WEIXIN(2,"weixin");

        private String msg;
        private Integer code;
        PAYPLATFORM(Integer code,String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


    public enum PAYONLINE{
        ONLINE(1,"线上支付"),
        OFFLINE(1,"线下支付");

        private Integer code;
        private String msg;

        PAYONLINE(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        PAYONLINE() {
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }



    public enum ORDERSTATUS{
      ORDER_CANCELLED(0,"已取消"),
      ORDER_NO_PAY(10,"未付款"),
      ORDER_PAY(20,"已付款"),
      ORDER_SEND(30,"已发货"),
      ORDER_SUCCESS(50,"交易成功"),
      ORDER_CLOSE(60,"交易关闭"),
        ;

        private int code;
        private String msg;

        ORDERSTATUS(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        ORDERSTATUS() {
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

        public static  ORDERSTATUS codeOf(int status){
            for(ORDERSTATUS os :ORDERSTATUS.values()){
                if(os.getCode() == status){
                    return os;
                }
            }
            return null;
        }
    }


}
