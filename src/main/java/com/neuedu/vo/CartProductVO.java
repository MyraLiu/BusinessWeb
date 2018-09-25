package com.neuedu.vo;

import com.neuedu.pojo.Category;
/**
 * 前端购物车商品实体类
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CartProductVO implements Serializable {
// 商品的属性
    private Integer productId;// '商品ID',
    private Integer categoryId;//分类id
    private String name;//商品名称
    private String subtitle;//`商品副标题
    private String main_image;//产品主图,url相对地址
    private String sub_images;//图片地址，json格式
    private String detail;//商品详情',
    private BigDecimal productPrice;//价格，单位-元保留两位小数',

    private Integer status;//商品状态 1-在售 2-下架 3-删除',
    private Date create_time;//'创建时间',
    private Date update_time;//'更新时间',
private Integer stock;
    private String limitQuantity;//库存描述
    private String imageHost;
    // 购物车的属性
    // 对应数据库中的属性
    private Integer cartId; // 购物车编号
    private Integer user_id; //  用户id
    private Integer quantity; // 商品数量
    private Integer checked; // 是否选择，1=已勾选,0=未勾选',
    private BigDecimal totalPrice;// 总的价格

    @Override
    public String toString() {
        return "CartProductVO{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", main_image='" + main_image + '\'' +
                ", sub_images='" + sub_images + '\'' +
                ", detail='" + detail + '\'' +
                ", productPrice=" + productPrice +
                ", status=" + status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", stock=" + stock +
                ", limitQuantity='" + limitQuantity + '\'' +
                ", imageHost='" + imageHost + '\'' +
                ", cartId=" + cartId +
                ", user_id=" + user_id +
                ", quantity=" + quantity +
                ", checked=" + checked +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public CartProductVO() {
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getSub_images() {
        return sub_images;
    }

    public void setSub_images(String sub_images) {
        this.sub_images = sub_images;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
