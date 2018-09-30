package com.neuedu.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderProductVO<T>  implements Serializable{

    private List<T> list;
    private String imageHost;
    private BigDecimal totalPrice;

    public OrderProductVO(List<T> list, String imageHost, BigDecimal totalPrice) {
        this.list = list;
        this.imageHost = imageHost;
        this.totalPrice = totalPrice;
    }

    public OrderProductVO() {
    }

    @Override
    public String toString() {
        return "OrderProductVO{" +
                "list=" + list +
                ", imageHost='" + imageHost + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
