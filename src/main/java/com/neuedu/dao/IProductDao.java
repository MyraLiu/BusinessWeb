package com.neuedu.dao;

import com.neuedu.pojo.Product;

public interface IProductDao {

    /*根据商品id查询商品信息*/
    Product findProductById(int id);
}
