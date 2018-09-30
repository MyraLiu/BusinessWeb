package com.neuedu.dao;

import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;
import com.neuedu.pojo.Pay;

import java.util.List;

public interface IOrderDao {

    /**
     * 根据订单编号和用户id查询订单信息
     * @param orderNo
     * @param userid
     * @return
     */
    public Order findOrderByOrderNoAndUserid(Long orderNo, Integer userid);
    public Order findOrderByOrderNo(Long orderNo);

    /**
     * 根据订单号查询订单明细
     * @param orderNo
     * @return
     */
    List<OrderItem> findOrderItemByOrderNo(Long orderNo);

    /**
     * 根据订单号修改订单状态
     * @param status
     * @param orderNo
     * @return
     */
    Integer updateOrderStatusByOrderNo(Integer status,Long orderNo);

    /**
     * 根据订单id查询用户id
     * @param orderNo
     * @return
     */
    Integer findUseridByOrderNo(Long orderNo);

    /**
     * 添加支付信息
     * @param pay
     * @return
     */
    Integer addPayInfo(Pay pay);

    /**
     * 创建订单
     * @param order
     * @return
     */
    Integer createOrder(Order order);

    /**
     * 批量插入订单商品信息
     * @param list
     * @return
     */
    Integer batchInsertOrderItem(List<OrderItem> list);

    /**
     * 用户的全部订单
     * @param pagenum
     * @param pagesize
     * @param userid
     * @return
     */
    List<Order> listOrder(Integer pagenum, Integer pagesize, Integer userid);

    /**
     * 商户查询所有订单
     * @param pagenum
     * @param pagesize
     * @return
     */
    List<Order> listAllOrder(Integer pagenum, Integer pagesize);

    /**
     * 获取用户总订单数
     * @param userid
     * @return
     */
   Integer countOrders(Integer userid);

    /**
     * 获取商户订单总数
     * @return
     */
   Integer countAllOrders();

   Order searchByOrderNo(Long orderNo);


}
