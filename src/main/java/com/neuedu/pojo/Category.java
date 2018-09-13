package com.neuedu.pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Category implements Serializable {

      private Integer id;// int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
    private Integer parent_id;//int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点，一级类别',
    private List<Product>  productList;//该类商品列表
       private String name;//varchar(50) DEFAULT NULL COMMENT '类别名称',
    private Integer status;//tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常，2-已废弃',
    private Integer sort_order;//int(4) DEFAULT NULL COMMENT '排序编号，同类展示顺序，数值相等则自然排序',
    private Date create_time;//datetime DEFAULT NULL COMMENT '创建时间',
    private Date update_time;//datetime DEFAULT NULL COMMENT '更新时间',


    public Category() {
    }
@PostConstruct
    public void init(){
        System.out.println("====category====init=====");
    }
@PreDestroy
    public void destory(){
        System.out.println("====category====destory=====");
    }


    public Category(Integer id, Integer parent_id, List<Product> productList, String name, Integer status, Integer sort_order, Date create_time, Date update_time) {
        this.id = id;
        this.parent_id = parent_id;
        this.productList = productList;
        this.name = name;
        this.status = status;
        this.sort_order = sort_order;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", productList=" + productList +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sort_order=" + sort_order +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }
}
