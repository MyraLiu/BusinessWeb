package com.neuedu.dao;

import com.neuedu.pojo.Product;

import java.util.List;

public interface IProductDao {

    /*根据商品id查询商品信息*/
    Product findProductById(int id);
    /*添加商品*/
    int addProduct(Product p);

    /*更新商品*/
    int updateProduct(Product p);

    /**
     * 分页查询商品数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Product> findProductByPageNo(Integer pageNo, Integer pageSize);
/**
 * */
public Long findTotalRecord();


    /**
     * 按照id或者名查询
     * @param id
     * @param name
     * @return
     */
    List<Product> findProductByIdOrName(Integer id,String name,Integer pageNo, Integer pageSize);

    public Long findTotalRecord(Integer id,String name);
}
