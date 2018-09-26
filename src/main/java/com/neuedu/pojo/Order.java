package com.neuedu.pojo;

import com.neuedu.common.BigDecimalUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    
    private Integer id;// int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    private Long order_no;// bigint(20) DEFAULT NULL COMMENT '订单号',
    private Integer user_id;// int(11) DEFAULT NULL COMMENT '用户id',
    private Integer shipping_id;// int(11) DEFAULT NULL,
 private BigDecimal payment;// decimal(20,2) DEFAULT NULL COMMENT '实际付款金额，单位是元，保留两位小数',
    private Integer payment_type;// int(4) DEFAULT NULL COMMENT '支付类型，1-在线支付',
    private Integer postage;// int(10) DEFAULT NULL COMMENT '运费,单位是元',
    private Integer status;// int(10) DEFAULT NULL COMMENT '订单状态：0-已取消 10-未付款  20-已付款 40-已发货 50-交易成功 60-交易失败',
           private Date payment_time ;//datetime DEFAULT NULL COMMENT '支付时间',
          private Date send_time;// datetime DEFAULT NULL COMMENT '发货时间',
          private Date  end_time ;//datetime DEFAULT NULL COMMENT '交易完成时间',
    private Date   close_time ;//datetime DEFAULT NULL COMMENT '交易关闭时间',
    private Date     create_time;// datetime DEFAULT NULL COMMENT '创建时间',
    private Date      update_time;// datetime DEFAULT NULL COMMENT '更新时间',


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_no=" + order_no +
                ", user_id=" + user_id +
                ", shipping_id=" + shipping_id +
                ", payment=" + payment +
                ", payment_type=" + payment_type +
                ", postage=" + postage +
                ", status=" + status +
                ", payment_time=" + payment_time +
                ", send_time=" + send_time +
                ", end_time=" + end_time +
                ", close_time=" + close_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public Order() {
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

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
