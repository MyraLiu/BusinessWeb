package com.neuedu.dao.impl;

import com.neuedu.common.MyBatisUtils;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Product;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements IProductDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public Product findProductById(int id) {

        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findProductById",id);
    }

    @Override
    public int addProduct(Product p) {

        return sqlSession.insert("com.neuedu.dao.IProductDao.addProduct",p);
    }

    @Override
    public int updateProduct(Product p) {
        return sqlSession.update("com.neuedu.dao.IProductDao.updateProduct",p);
    }

    @Override
    public List<Product> findProductByPageNo(Integer pageNo, Integer pageSize) {
//        System.out.println("pageno="+(pageNo-1)*pageSize+",pagesize="+pageSize);

        Map<String,Integer> map = new HashMap<>();
        map.put("offSet",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        return sqlSession.selectList("com.neuedu.dao.IProductDao.findProductByPageNo",map);

    }

    @Override
    public Long findTotalRecord() {
        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findTotalRecord");
    }

    @Override
    public List<Product> findProductByIdOrName(Integer id, String name,Integer pageNo, Integer pageSize) {

        Map<String ,Object> map = new HashMap<>();
        map.put("productId",id);
        map.put("productName",name);
        map.put("offSet",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        return sqlSession.selectList("com.neuedu.dao.IProductDao.findProductByIdOrName",map);
    }

    @Override
    public Long findTotalRecord(Integer id, String name) {
        Map<String ,Object> map = new HashMap<>();
        map.put("productId",id);
        map.put("productName",name);

        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findTotalRecordByIdOrName",map);
    }


}
