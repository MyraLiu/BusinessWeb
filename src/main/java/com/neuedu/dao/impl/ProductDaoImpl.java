package com.neuedu.dao.impl;

import com.neuedu.common.MyBatisUtils;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Product;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.image.ImageProducer;
@Repository
public class ProductDaoImpl implements IProductDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public Product findProductById(int id) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
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
}
