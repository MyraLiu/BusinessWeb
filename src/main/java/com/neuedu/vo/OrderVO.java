package com.neuedu.vo;

import com.sun.corba.se.spi.activation.Server;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO<T> implements Serializable{
    private Integer id;// int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    private Long order_no;// bigint(20) DEFAULT NULL COMMENT '订单号',
    private Integer user_id;// int(11) DEFAULT NULL COMMENT '用户id',
    private Integer shipping_id;// int(11) DEFAULT NULL,
    private BigDecimal payment;// decimal(20,2) DEFAULT NULL COMMENT '实际付款金额，单位是元，保留两位小数',
    private Integer payment_type;// int(4) DEFAULT NULL COMMENT '支付类型，1-在线支付',
    private String paymentDesc;//支付方式描述
    private Integer postage;// int(10) DEFAULT NULL COMMENT '运费,单位是元',
    private Integer status;// int(10) DEFAULT NULL COMMENT '订单状态：0-已取消 10-未付款  20-已付款 40-已发货 50-交易成功 60-交易失败',
   private  String statusDesc;//订单状态描述
    private String payment_time ;//datetime DEFAULT NULL COMMENT '支付时间',
    private String send_time;// datetime DEFAULT NULL COMMENT '发货时间',
    private String  end_time ;//datetime DEFAULT NULL COMMENT '交易完成时间',
    private String   close_time ;//datetime DEFAULT NULL COMMENT '交易关闭时间',
    private String     create_time;// datetime DEFAULT NULL COMMENT '创建时间',
    private String      update_time;// datetime DEFAULT NULL COMMENT '更新时间',
    private String imageHost;// uri 域名
    private String receiverName;// 收货人姓名
    private ShippingVO shippingVO;// 地址信息
private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "id=" + id +
                ", order_no=" + order_no +
                ", user_id=" + user_id +
                ", shipping_id=" + shipping_id +
                ", payment=" + payment +
                ", payment_type=" + payment_type +
                ", paymentDesc='" + paymentDesc + '\'' +
                ", postage=" + postage +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", payment_time='" + payment_time + '\'' +
                ", send_time='" + send_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", close_time='" + close_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", imageHost='" + imageHost + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", shippingVO=" + shippingVO +
                '}';
    }

    public OrderVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Long order_no) {
        this.order_no = order_no;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(Integer shipping_id) {
        this.shipping_id = shipping_id;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(Integer payment_type) {
        this.payment_type = payment_type;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public ShippingVO getShippingVO() {
        return shippingVO;
    }

    public void setShippingVO(ShippingVO shippingVO) {
        this.shippingVO = shippingVO;
    }
}
