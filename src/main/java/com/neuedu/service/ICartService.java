package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Cart;
import com.neuedu.vo.CartProductVO;
import com.neuedu.vo.CartVO;

import java.util.List;

public interface ICartService {

    /**
     * 添加商品
     * @param userid 用户id
     * @param productid  商品id
     * @param count  添加的数量
     * @return
     */
    public ServerResponse<CartVO> addProductToCart(Integer userid,Integer productid,Integer count);

    /**
     * 修改商品信息
     * @param userid
     * @param productid
     * @param count
     * @return
     */
    ServerResponse<CartVO> updateProductInCart(Integer userid,Integer productid,Integer count);

    /**
     * 删除列表中的商品
     * @param productids
     * @return
     */
    ServerResponse<CartVO> removeProduct(Integer[] productids,Integer userid);

    /**
     * 选中商品 和取消选中
     * @param productid
     * @param userid
     * @return
     */
    ServerResponse<CartVO> checkProduct(Integer productid,Integer userid);

    /**
     * 选中商品 和取消选中
     * @param productid
     * @param userid
     * @return
     */
    ServerResponse<CartVO> uncheckProduct(Integer productid,Integer userid);

    /**
     * 全选
     * @param userid
     * @return
     */
    ServerResponse<CartVO> selectAll(Integer userid);

    /**
     * 全不选
     * @param userid
     * @return
     */
    ServerResponse<CartVO> selectNone(Integer userid);

    /**
     * 购物车列表
     * @param userid
     * @return
     */
    ServerResponse<CartVO> listCart(Integer userid);

    /**
     * 查询购物车中商品数量
     * @param userid
     * @return
     */
    ServerResponse<Integer> sumProducts(Integer userid);

}
