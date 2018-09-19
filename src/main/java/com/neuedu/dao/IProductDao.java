package com.neuedu.dao;

import com.neuedu.pojo.Product;

public interface IProductDao {

    /*根据商品id查询商品信息*/
    Product findProductById(int id);
    /*添加商品*/
    int addProduct(Product p);

    /*更新商品*/
    int updateProduct(Product p);

    /*   */


    /*  */


}
