package com.neuedu.dao.impl;

import com.neuedu.dao.ICartDao;
import com.neuedu.pojo.Cart;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CartDaoImpl implements ICartDao {
   @Autowired
   private SqlSession sqlSession;


    @Override
    public Cart findCartByUseridAndProductid(Integer userid, Integer productid) {
        Map<String ,Integer> map = new HashMap<>();
        map.put("userid",userid);
        map.put("productid",productid);
        return sqlSession.selectOne("com.neuedu.dao.ICartDao.findCartByUseridAndProductid",map);
    }

    @Override
    public Integer addProductToCart(Cart cart) {

        return sqlSession.insert("com.neuedu.dao.ICartDao.addProductToCart",cart);
    }

    @Override
    public Integer updateProductInCart(Cart cart) {

        return sqlSession.update("com.neuedu.dao.ICartDao.updateProductInCart",cart);
    }

    @Override
    public List<Cart> findCartsByUserid(Integer userid) {
       return sqlSession.selectList("com.neuedu.dao.ICartDao.findCartsByUserid",userid);

    }

    @Override
    public Integer isAllChecked(Integer userid) {
        return sqlSession.selectOne("com.neuedu.dao.ICartDao.isAllChecked",userid);
    }

    @Override
    public Integer removeProduct(List<Integer> productids, Integer userid) {
        Map<String ,Object> map = new HashMap<>();
        map.put("productids" ,productids);
        map.put("userid",userid);

        return sqlSession.delete("com.neuedu.dao.ICartDao.removeProduct",map);
    }

    @Override
    public Integer checkAndUncheck(Cart cart) {
        return sqlSession.update("com.neuedu.dao.ICartDao.checkAndUncheck",cart);
    }

    @Override
    public Integer checkAllOrNone(Integer userid, Integer check) {
        Map<String,Integer> map = new HashMap<>();
        map.put("user_id",userid);
        map.put("checked",check);
        return  sqlSession.update("com.neuedu.dao.ICartDao.checkAllOrNone",map);
    }

    @Override
    public Integer sumProducts(Integer userid) {

        return sqlSession.selectOne("com.neuedu.dao.ICartDao.sumProducts",userid);
    }
}
