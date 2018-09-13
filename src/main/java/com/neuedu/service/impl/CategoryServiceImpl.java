package com.neuedu.service.impl;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ICategoryDao;
import com.neuedu.dao.impl.CategoryDaoImpl;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public class CategoryServiceImpl implements ICategoryService {

    ICategoryDao categoryDao;


    public CategoryServiceImpl() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        categoryDao = (ICategoryDao) applicationContext.getBean("categoryDaoImpl");
//        CategoryDaoImpl cd2 = (CategoryDaoImpl) applicationContext.getBean("categoryDaoImpl");
//        System.out.println(categoryDao);
//        System.out.println(cd2);
//        System.out.println(cd2==categoryDao);
//
//        System.out.println(cd2.getCategoryId());
//        System.out.println("====Category===="+cd2.getCategory());
        System.out.println("======="+categoryDao);
    }

    @Override
    public List<Category> findSubCategoryById(int id) {
        return   categoryDao.findSubCategoryById(id);
    }

    @Override
    public int addCategory(int parent_id, String name) {
        return categoryDao.addCategory(parent_id,name);
    }

    @Override
    public int updateCategoryName(int categoryid, String name) {
        return categoryDao.updateCategoryName(categoryid,name);
    }

    @Override
    public ServerResponse findAllSubCategory(int id) {
        return null;
    }


}
