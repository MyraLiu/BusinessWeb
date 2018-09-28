package com.neuedu.service;

import com.mysql.fabric.Server;
import com.neuedu.common.ServerResponse;

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
}
