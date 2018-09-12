package com.neuedu.dao.impl;

import com.neuedu.common.MyBatisUtils;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Product;
import org.apache.ibatis.session.SqlSession;

import java.awt.image.ImageProducer;

public class ProductDaoImpl implements IProductDao {
    @Override
    public Product findProductById(int id) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findProductById",id);
    }
}
