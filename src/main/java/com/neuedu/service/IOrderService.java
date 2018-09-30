package com.neuedu.service;

import com.mysql.fabric.Server;
import com.neuedu.common.ServerResponse;
import com.neuedu.vo.OrderVO;

import java.util.Map;

public interface IOrderService  {


    /***
     * 预订单生成支付二维码
     * @param orderid
     * @param userid
     * @return
     */
    ServerResponse pay(Long orderid, Integer userid);


    /**
     * 查询支付状态
     * @param orderid
     * @return
     */
    ServerResponse<Boolean> queryOrderPayStatus(Long orderid);

    /**
     * 支付完成回调
     * @param map
     * @return
     */
    String callback(Map<String,String> map);

    /**
     * 创建订单
     * @param shippingid
     * @param userid
     * @return
     */
    ServerResponse createOrder(Integer shippingid,Integer userid);


    /**
     * 取消订单
     * @param orderNo
     * @param userid
     * @return
     */
    ServerResponse cancelOrder(Long orderNo,Integer userid);

    /**
     * 获取订单中商品详情
     * @param userid
     * @return
     */
    ServerResponse getCartProductInfo( Integer userid);

    /**
     * 获取订单详情
     * @param orderNo
     * @param userid
     * @return
     */
    ServerResponse getOrderDetail(Long orderNo, Integer userid);

    /**
     * 发货
     * @param orderNo
     * @return
     */
    ServerResponse sendOrder(Long orderNo);


    /**
     * 获取用户的订单列表
     * @param pagenum
     * @param pagesize
     * @param userid
     * @return
     */
    ServerResponse listOrder(Integer pagenum,Integer pagesize,Integer userid);


    /**
     * 获取全部的订单列表
     * @param pagenum
     * @param pagesize
     * @return
     */
    ServerResponse listAllOrder(Integer pagenum,Integer pagesize);

    /**
     * 按照订单号搜索订单信息
     * @param orderNo
     * @return
     */

    ServerResponse<OrderVO> searchOrder(Long orderNo);

    /**
     * 按照订单查询订单详细信息
     * @param orderNo
     * @return
     */

    ServerResponse<OrderVO> getOrderDetail(Long orderNo);

}
