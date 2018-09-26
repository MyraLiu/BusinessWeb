package com.neuedu.dao;

import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;

import java.util.List;

public interface IOrderDao {

    /**
     * 根据订单编号和用户id查询订单信息
     * @param orderNo
     * @param userid
     * @return
     */
    public Order findOrderByOrderNoAndUserid(Long orderNo,Integer userid);

    /**
     * 根据订单号查询订单明细
     * @param orderNo
     * @return
     */
    List<OrderItem> findOrderByOrderNo(Long orderNo);
}
