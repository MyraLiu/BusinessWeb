package com.neuedu.dao.impl;

import com.neuedu.dao.IOrderDao;
import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;
import com.neuedu.pojo.Pay;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements IOrderDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public Order findOrderByOrderNoAndUserid(Long orderNo, Integer userid) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",orderNo);
        map.put("userid",userid);

        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.findOrderByOrderNoAndUserid",map);
    }

    @Override
    public Order findOrderByOrderNo(Long orderNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",orderNo);

        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.findOrderByOrderNo",map);

    }

    @Override
    public List<OrderItem> findOrderItemByOrderNo(Long orderNo) {
       return sqlSession.selectList("com.neuedu.dao.IOrderDao.findOrderItemByOrderNo",orderNo);
    }

    @Override
    public Integer updateOrderStatusByOrderNo(Integer status, Long orderNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("orderNo",orderNo);
        return sqlSession.update("com.neuedu.dao.IOrderDao.updateOrderStatusByOrderNo",map);
    }

    @Override
    public Integer findUseridByOrderNo(Long orderNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",orderNo);
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.findUseridByOrderNo",map);
    }

    @Override
    public Integer addPayInfo(Pay pay) {
        return sqlSession.insert("com.neuedu.dao.IOrderDao.addPayInfo",pay);
    }

    @Override
    public Integer createOrder(Order order) {
        return sqlSession.insert("com.neuedu.dao.IOrderDao.createOrder",order);
    }

    @Override
    public Integer batchInsertOrderItem(List<OrderItem> list) {
        return sqlSession.insert("com.neuedu.dao.IOrderDao.batchInsertOrderItem",list);
    }


}
