package com.neuedu.dao;

import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;

import java.util.List;
import java.util.Set;

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

    /**
     * 根据商品id或商品名称 获取商品总数量
     * @param id
     * @param name
     * @return
     */
    public Long findTotalRecord(Integer id,String name);

    /**
     *
     * * 根据商品id或商品名称 获取商品总数量

     * @param ids
     * @param productName
     * @return
     */

    public Long findTotalRecord(Set<Integer> ids,String productName);


    /**
     * 根据i的查询在售商品
     * @param productId
     * @return
     */
    public Product  findProductByIdAndOnline(Integer productId);

    /**
     * 根据分类id的集合和商品名称搜索商品
     * @param categoryIds
     * @param productName
     * @param pageNo
     * @param pageSize
     * @return
     */


    public List<Product> findProductsByCategoryIdsAndProductName(Set<Integer> categoryIds, String productName, Integer pageNo, Integer pageSize,String orderby);
}
