package com.neuedu.dao.impl;

import com.neuedu.dao.IOrderDao;
import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;
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
    public List<OrderItem> findOrderByOrderNo(Long orderNo) {
       return sqlSession.selectList("com.neuedu.dao.IOrderDao.findOrderByOrderNo",orderNo);
    }


}
