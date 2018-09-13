package com.neuedu.dao.impl;

import com.neuedu.common.MyBatisUtils;
import com.neuedu.dao.ICategoryDao;
import com.neuedu.pojo.Category;
import org.apache.ibatis.javassist.CtBehavior;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.*;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

    @Autowired
    private SqlSession sqlSession;

    private int categoryId;
    /*@Autowired
    @Qualifier("category1")*/
    //@Resource(name="category1")
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDaoImpl(Category category) {
        this.category = category;
        System.out.println("======CategoryDaoImpl==Category====");
    }

    public int getCategoryId() {
        return categoryId;
    }

    public CategoryDaoImpl(int categoryId) {
        this.categoryId = categoryId;
        System.out.println("======CategoryDaoImpl构造方法==categoryId===");
    }

    public CategoryDaoImpl() {
        System.out.println("======CategoryDaoImpl构造方法==无参===");
    }

    public void init() {
        System.out.println("======CategoryDaoImpl-init方法=====");
    }

    public void destory() {
        System.out.println("======CategoryDaoImpl-destory方法=====");
    }


    @Override
    public Category findCategoryById(int id) {
//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        System.out.println(id);
        return sqlSession.selectOne("com.neuedu.dao.ICategoryDao.findCategoryById", id);
    }

    @Override
    public List<Category> findSubCategoryById(int id) {

//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
    List<Category> listc = sqlSession.selectList("com.neuedu.dao.ICategoryDao.findSubCategoryById", id);
        return  listc;
    }

    @Override
    public int addCategory(int parent_id, String name) {
//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Map<String, Object> map = new HashMap<>();
        map.put("parentid", parent_id);
        map.put("categoryname", name);
        int result = sqlSession.insert("com.neuedu.dao.ICategoryDao.addCategory", map);
        sqlSession.commit();
        return result;

    }

    @Override
    public int updateCategoryName(int categoryid, String newname) {
//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Map<String, Object> map = new HashMap<>();
        map.put("categoryid", categoryid);
        map.put("categoryname", newname);
        int result = sqlSession.update("com.neuedu.dao.ICategoryDao.updateCategoryName", map);
        sqlSession.commit();
        return result;
    }

    @Override
    public Set<Category> findAllSubCategory(int id) {
        Set<Category> sc = new LinkedHashSet<>();
        sc = findAll(sc, id);
        return sc;
    }


    /**
     * 递归查询子分类，及添加分类id
     *
     * @param sc
     * @param id
     * @return
     */
    private static Set<Category> findAll(Set<Category> sc, int id) {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.findCategoryById(id);
        if (category != null) {
            sc.add(category);
        }
        List<Category> listc = categoryDao.findSubCategoryById(id);
        for (Category c : listc) {
            findAll(sc, c.getId());
        }
        return sc;
    }

    /*
    *
    *
[Category{id=100032, parent_id=0, productList=[], name='电子产品', status=1, sort_order=1, create_time=Mon Sep 10 19:49:27 CST 2018, update_time=Mon Sep 10 19:49:27 CST 2018},


Category{id=100100, parent_id=100032, productList=[Product{id=10000, category_id=100100, category=null, name='MI6', subtitle='null', main_image='null', sub_images='null', detail='null', price=1999.00, stock=1000, status=null, create_time=Mon Sep 10 19:58:35 CST 2018, update_time=Mon Sep 10 19:58:35 CST 2018}, Product{id=10001, category_id=100100, category=null, name='MI7', subtitle='null', main_image='null', sub_images='null', detail='null', price=2999.00, stock=1000, status=null, create_time=Mon Sep 10 19:58:35 CST 2018, update_time=Mon Sep 10 19:58:35 CST 2018}, Product{id=10002, category_id=100100, category=null, name='MI7', subtitle='null', main_image='null', sub_images='null', detail='null', price=3999.00, stock=1000, status=null, create_time=Mon Sep 10 19:58:35 CST 2018, update_time=Mon Sep 10 19:58:35 CST 2018}], name='手机', status=1, sort_order=1, create_time=Mon Sep 10 19:53:27 CST 2018, update_time=Mon Sep 10 19:53:27 CST 2018},


Category{id=100101, parent_id=100032, productList=[Product{id=10003, category_id=100101, category=null, name='华硕k80', subtitle='null', main_image='null', sub_images='null', detail='null', price=1999.00, stock=1000, status=null, create_time=Mon Sep 10 19:59:59 CST 2018, update_time=Mon Sep 10 19:59:59 CST 2018}, Product{id=10004, category_id=100101, category=null, name='联想e30', subtitle='null', main_image='null', sub_images='null', detail='null', price=2999.00, stock=1000, status=null, create_time=Mon Sep 10 19:59:59 CST 2018, update_time=Mon Sep 10 19:59:59 CST 2018}, Product{id=10005, category_id=100101, category=null, name='惠普s1', subtitle='null', main_image='null', sub_images='null', detail='null', price=3999.00, stock=1000, status=null, create_time=Mon Sep 10 19:59:59 CST 2018, update_time=Mon Sep 10 19:59:59 CST 2018}], name='电脑', status=1, sort_order=1, create_time=Mon Sep 10 19:53:27 CST 2018, update_time=Mon Sep 10 19:53:27 CST 2018},

Category{id=100106, parent_id=100101, productList=[], name='notepad', status=1, sort_order=null, create_time=null, update_time=null}, Category{id=100102, parent_id=100032, productList=[], name='音响', status=1, sort_order=1, create_time=Mon Sep 10 19:53:27 CST 2018, update_time=Mon Sep 10 19:53:27 CST 2018},

Category{id=100103, parent_id=100032, productList=[], name='液晶显示器', status=1, sort_order=1, create_time=Mon Sep 10 19:53:27 CST 2018, update_time=Mon Sep 10 19:53:27 CST 2018},


Category{id=100105, parent_id=100032, productList=[], name='家用电器', status=1, sort_order=null, create_time=null, update_time=null}]

*/

 /*   public static List<Category> findAll(int id){
        CategoryDaoImpl cd = new CategoryDaoImpl();
        List<Category>list=cd.findSubCategoryById(id);
        if(list==null||list.size()<=0){
            return null;
        }
        for (Category c:list) {
            List<Category> listsub=cd.findAll(c.getId());
            if(listsub!=null&&listsub.size()>0) {
                list.addAll(listsub);
            }
        }
        return list;

    }

    public static void printAll(int id){
        CategoryDaoImpl cd = new CategoryDaoImpl();
        List<Category>list=cd.findSubCategoryById(id);
        if(list==null||list.size()<=0){
            return ;
        }
        System.out.println(list);
        for (Category c:list) {
            List<Category> listsub=cd.findAll(c.getId());
            if(listsub!=null&&listsub.size()>0) {
//                list.addAll(listsub);
                System.out.println(listsub);
            }
        }


    }*/


 /*   public static void main(String[] args) {
        System.out.println(findAll(100032));
//        printAll(100032);
    }*/
}
