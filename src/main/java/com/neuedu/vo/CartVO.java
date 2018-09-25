package com.neuedu.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 前端购物车实体类
 */
public class CartVO implements Serializable {

    private List<CartProductVO> cartProductVOList;
    private Boolean isAllChecked;
    private BigDecimal totalPrice;


    @Override
    public String toString() {
        return "CartVO{" +
                "cartProductVOList=" + cartProductVOList +
                ", isAllChecked=" + isAllChecked +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public CartVO() {
    }

    public List<CartProductVO> getCartProductVOList() {
        return cartProductVOList;
    }

    public void setCartProductVOList(List<CartProductVO> cartProductVOList) {
        this.cartProductVOList = cartProductVOList;
    }

    public Boolean getAllChecked() {
        return isAllChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        isAllChecked = allChecked;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartVO(List<CartProductVO> cartProductVOList, Boolean isAllChecked, BigDecimal totalPrice) {

        this.cartProductVOList = cartProductVOList;
        this.isAllChecked = isAllChecked;
        this.totalPrice = totalPrice;
    }
}
