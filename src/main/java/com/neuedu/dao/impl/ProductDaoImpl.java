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
import java.util.Set;

@Repository
public class ProductDaoImpl implements IProductDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public Product findProductById(int id) {

        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findProductById", id);
    }

    @Override
    public int addProduct(Product p) {

        return sqlSession.insert("com.neuedu.dao.IProductDao.addProduct", p);
    }

    @Override
    public int updateProduct(Product p) {
        return sqlSession.update("com.neuedu.dao.IProductDao.updateProduct", p);
    }

    @Override
    public List<Product> findProductByPageNo(Integer pageNo, Integer pageSize) {
//        System.out.println("pageno="+(pageNo-1)*pageSize+",pagesize="+pageSize);

        Map<String, Integer> map = new HashMap<>();
        map.put("offSet", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        return sqlSession.selectList("com.neuedu.dao.IProductDao.findProductByPageNo", map);

    }

    @Override
    public Long findTotalRecord() {
        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findTotalRecord");
    }

    @Override
    public List<Product> findProductByIdOrName(Integer id, String name, Integer pageNo, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("productId", id);
        map.put("productName", name);
        map.put("offSet", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        return sqlSession.selectList("com.neuedu.dao.IProductDao.findProductByIdOrName", map);
    }

    @Override
    public Long findTotalRecord(Integer id, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("productId", id);
        map.put("productName", name);

        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findTotalRecordByIdOrName", map);
    }

    @Override
    public Long findTotalRecord(Set<Integer> categoryIds, String productName) {

        // 查询参数
        Map<String, Object> map = new HashMap<>();
        if (categoryIds != null && categoryIds.size() > 0) {
            map.put("categoryIds", categoryIds);
        } else {
            map.put("categoryIds", null);
        }
        map.put("productName", productName);

        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findTotalRecordByIdsOrName", map);
    }

    @Override
    public Product findProductByIdAndOnline(Integer productId) {
        return sqlSession.selectOne("com.neuedu.dao.IProductDao.findProductByIdAndOnline", productId);
    }

    @Override
    public List<Product> findProductsByCategoryIdsAndProductName(Set<Integer> categoryIds, String productName, Integer pageNo, Integer pageSize,String orderby) {
        Integer sortnum=null;
        // 查询参数
        Map<String, Object> map = new HashMap<>();
        if (categoryIds != null && categoryIds.size() > 0) {
            map.put("categoryIds", categoryIds);
        } else {
            map.put("categoryIds", null);
        }

        map.put("productName", productName);

        map.put("offSet", (pageNo - 1)*pageSize);
        map.put("pageSize", pageSize);
        if(orderby!=null&&!orderby.equals("")) {
            if (orderby.equals("asc") || orderby.equals("ASC")) {
                sortnum = 1;
            } else if (orderby.equals("desc") || orderby.equals("DESC")) {
                sortnum = 2;
            }
        }
        System.out.println(sortnum);
        map.put("sortnum",sortnum);
        return sqlSession.selectList("com.neuedu.dao.IProductDao.findProductsByCategoryIdsAndProductName", map);
    }


}
