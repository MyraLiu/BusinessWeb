package com.neuedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Shipping implements Serializable {

   private Integer  id;// int(11) NOT NULL AUTO_INCREMENT,
    private Integer user_id;// int(11) DEFAULT NULL COMMENT '用户id',
   private String receiver_name;// varchar(20) DEFAULT NULL COMMENT '收货姓名',
    private String receiver_phone;// varchar(20) DEFAULT NULL COMMENT '收货固定电话',
    private String receiver_mobile;// varchar(20) DEFAULT NULL COMMENT '收货移动电话',
    private String receiver_province;// varchar(20) DEFAULT NULL COMMENT '省份',
    private String receiver_city;// varchar(20) DEFAULT NULL COMMENT '城市',
    private String receiver_district;// varchar(20) DEFAULT NULL COMMENT '区/县',
    private String receiver_address;// varchar(200) DEFAULT NULL COMMENT '详细地址',
    private String receiver_zip;// varchar(6) DEFAULT NULL COMMENT '邮编',
    private Date create_time;// datetime DEFAULT NULL COMMENT '创建时间',
    private Date update_time;// datetime DEFAULT NULL COMMENT '更新时间',


    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_phone='" + receiver_phone + '\'' +
                ", receiver_mobile='" + receiver_mobile + '\'' +
                ", receiver_province='" + receiver_province + '\'' +
                ", receiver_city='" + receiver_city + '\'' +
                ", receiver_district='" + receiver_district + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", receiver_zip='" + receiver_zip + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public Shipping() {
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

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_zip() {
        return receiver_zip;
    }

    public void setReceiver_zip(String receiver_zip) {
        this.receiver_zip = receiver_zip;
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
