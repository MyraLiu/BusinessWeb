package com.neuedu.common;

import org.omg.CORBA.NO_PERMISSION;

public enum ResponseCode {

    SUCCESS(0,"成功"),
    NEED_LOGIN(1,"需要登录"),
    NO_PERMISSION(2,"无权限"),
    GETSUBCATEGORY_NEED_CATEGORYID(3,"categoryid必须"),
    NEED_PRODUCT(5,"商品参数必传"),
    NEED_PRODUCT_STATUS(6,"商品参数必须"),
    PRODUCT_OFFLINE(8,"商品不存在或者已经下架"),
    PRODUCT_NOT_IN_CART(9,"购物车中没有商品信息"),
    SHIPPING_NEED_MESSAGE(10,"地址中必须包含接收人姓名，电话，具体地址信息"),
    NEED_SHIPPINGID(11,"地址id必须"),
    NEED_ORDERNO(12,"订单号必须"),
    NOT_FOUND_ORDERNO(13,"没有该订单"),
    NO_ORDER_PERMISSION(14,"不支持的交易状态，交易返回异常!!!"),
    CART_EMPTY(15,"购物车空"),
    PRODUCT_STOCK_NOT_ENOUGH(16,"商品库存不足"),
    NOT_GET_ORDERITEM(17,"订单商品信息获取失败"),
    ORDER_CAN_NOT_CANCEL(18,"订单不能取消"),
    CART_NOT_FOUND_PRODUCT(19,"购物车中为找打商品"),
    ORDER_LIST_IS_NULL(20,"订单列表为空"),
    UNKNOWN(90,"系统异常，预下单状态未知!!!"),
    ORDER_FAIL(99,"支付宝预下单失败!!!"),
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
