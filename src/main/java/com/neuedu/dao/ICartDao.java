package com.neuedu.dao;

import com.neuedu.pojo.Cart;

import java.net.InetAddress;
import java.util.List;

public interface ICartDao {
    /**
     * 根据用户id和商品id查询购物车信息
     * @param userid
     * @param productid
     * @return
     */
    Cart findCartByUseridAndProductid(Integer userid, Integer productid);

    /**
     * 添加商品到购物车
     * @param cart
     * @return
     */
    Integer addProductToCart(Cart cart);

    /**
     * 更新购物车中商品信息
     * @param cart
     * @return
     */
    Integer updateProductInCart(Cart cart);

    /**
     * 根据用户id查询购物车清单
     * @param userid
     * @return
     */

    List<Cart> findCartsByUserid(Integer userid);

    /**
     * 判断用户购物车是否全选
     * @param userid
     * @return
     */
    Integer isAllChecked(Integer userid);

    /**
     * 删除商品信息
     * @param productids
     * @param userid
     * @return
     */
    Integer removeProduct(List<Integer> productids,Integer userid);

    /**
     * 选中和取消
     * @param cart
     * @return
     */
    Integer checkAndUncheck(Cart cart);

    /**
     * 全选或者全部选
     * @param userid
     * @param check
     * @return
     */
    Integer checkAllOrNone(Integer userid,Integer check);

    /**
     * 计算商品总数
     * @param userid
     * @return
     */
    Integer sumProducts(Integer userid);

}
