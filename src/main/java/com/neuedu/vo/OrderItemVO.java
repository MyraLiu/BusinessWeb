package com.neuedu.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderItemVO implements Serializable{
    private Integer id;// int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
    private Integer user_id;// int(11) DEFAULT NULL COMMENT '用户id',
    private Long order_no;// bigint(20) DEFAULT NULL COMMENT '订单号',
    private Integer product_id;// int(11) DEFAULT NULL COMMENT '商品id',
    private String product_name;// varchar(100) DEFAULT NULL COMMENT '商品名称',
    private String product_image;// varchar(500) DEFAULT NULL COMMENT '商品图片地址',
    private BigDecimal current_unit_price;// decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价,单位是元，保留两位小数',
    private Integer quantity;// int(10) DEFAULT NULL COMMENT '商品数量',
    private BigDecimal total_price;// decimal(20,2) DEFAULT NULL COMMENT '商品总价，单位是元，保留两位小数',
    private String create_time;// datetime DEFAULT NULL COMMENT '创建时间',
    private String  update_time;// datetime DEFAULT NULL COMMENT '更新时间',


    @Override
    public String toString() {
        return "OrderItemVO{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", order_no=" + order_no +
                ", product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_image='" + product_image + '\'' +
                ", current_unit_price=" + current_unit_price +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }

    public OrderItemVO() {
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

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public BigDecimal getCurrent_unit_price() {
        return current_unit_price;
    }

    public void setCurrent_unit_price(BigDecimal current_unit_price) {
        this.current_unit_price = current_unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
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
}
