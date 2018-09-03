package com.neuedu.pojo;

public class Product {
    private  Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
