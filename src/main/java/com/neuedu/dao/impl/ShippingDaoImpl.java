package com.neuedu.dao.impl;

import com.neuedu.dao.IShippingDao;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IShippingService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShippingDaoImpl implements IShippingDao {
    @Autowired
   private SqlSession sqlSession;
    @Override
    public Integer add(Shipping shipping) {

        return sqlSession.insert("com.neuedu.dao.IShippingDao.add",shipping);
    }

    @Override
    public Integer update(Shipping shipping) {
        return sqlSession.update("com.neuedu.dao.IShippingDao.update",shipping);
    }

    @Override
    public Integer remove(Integer shippingid) {
        return  sqlSession.delete("com.neuedu.dao.IShippingDao.remove",shippingid);
    }

    @Override
    public Shipping findByUser(Integer shippingid,Integer userid) {
        System.out.println(shippingid);
        Map<String ,Object> map = new HashMap<>();
        map.put("shippingid",shippingid);
        map.put("userid",userid);
        return  sqlSession.selectOne("com.neuedu.dao.IShippingDao.findByUser",map);
    }

    @Override
    public Shipping findByAdmin(Integer shippingid) {
        return  sqlSession.selectOne("com.neuedu.dao.IShippingDao.findByAdmin",shippingid);
    }

    @Override
    public List<Shipping> list(Integer userid,Integer pageNum,Integer pageSize,Integer orderby) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userid",userid);
        map.put("orderby",orderby);
        map.put("offset",(pageNum-1)*pageSize);
        map.put("pageSize",pageSize);
        return  sqlSession.selectList("com.neuedu.dao.IShippingDao.list",map);
    }

    @Override
    public List<Shipping> listAll(Integer pageNum, Integer pageSize, Integer orderby) {
        Map<String,Integer> map = new HashMap<>();
        map.put("orderby",orderby);
        map.put("offset",(pageNum-1)*pageSize);
        map.put("pageSize",pageSize);
        return  sqlSession.selectList("com.neuedu.dao.IShippingDao.listAll",map);
    }

    @Override
    public Integer findId(Shipping shipping) {
        return sqlSession.selectOne("com.neuedu.dao.IShippingDao.findId",shipping);
    }

    @Override
    public Integer count(Integer userid) {
        return sqlSession.selectOne("com.neuedu.dao.IShippingDao.count",userid);
    }

    @Override
    public Integer countAll() {
        return sqlSession.selectOne("com.neuedu.dao.IShippingDao.countAll");
    }


}
