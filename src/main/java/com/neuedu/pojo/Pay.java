package com.neuedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Pay implements Serializable {

    private Integer  id;// int(11) NOT NULL AUTO_INCREMENT,
    private Integer  user_id;// int(11) DEFAULT NULL COMMENT '用户id',
    private Long  order_no;// bigint(20) DEFAULT NULL COMMENT '订单号',
    private Integer  pay_platform;// int(10) DEFAULT NULL COMMENT '支付平台：1-支付宝，2-微信',
    private String platform_number;// varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
            private String platform_status;// varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
    private Date create_time;// datetime DEFAULT NULL COMMENT '创建时间',
    private Date  update_time;// datetime DEFAULT NULL COMMENT '更新时间',


    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", order_no=" + order_no +
                ", pay_platform=" + pay_platform +
                ", platform_number='" + platform_number + '\'' +
                ", platform_status='" + platform_status + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public Pay() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Long order_no) {
        this.order_no = order_no;
    }

    public Integer getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(Integer pay_platform) {
        this.pay_platform = pay_platform;
    }

    public String getPlatform_number() {
        return platform_number;
    }

    public void setPlatform_number(String platform_number) {
        this.platform_number = platform_number;
    }

    public String getPlatform_status() {
        return platform_status;
    }

    public void setPlatform_status(String platform_status) {
        this.platform_status = platform_status;
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
