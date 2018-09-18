package com.neuedu.service.impl;

import com.neuedu.common.ResponseCode;
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

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryDao categoryDao;


/*   public CategoryServiceImpl() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        categoryDao = (ICategoryDao) applicationContext.getBean("categoryDaoImpl");
       CategoryDaoImpl cd2 = (CategoryDaoImpl) applicationContext.getBean("categoryDaoImpl");
        System.out.println(categoryDao);
        System.out.println(cd2);
        System.out.println(cd2==categoryDao);

        System.out.println(cd2.getCategoryId());
        System.out.println("====Category===="+cd2.getCategory());
        System.out.println("======="+categoryDao);
    }*/

    @Override
    public ServerResponse<List<Category>> findSubCategoryById(int id) {
        List<Category> listc = categoryDao.findSubCategoryById(id);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), listc, ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<String> addCategory(int parent_id, String name) {
        int result = categoryDao.addCategory(parent_id, name);
        if (result > 0) {
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
        } else {
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }
    }

    public int addCategory1(int parent_id, String name) {
        return categoryDao.addCategory(parent_id, name);

    }

    @Override
    public ServerResponse<String> updateCategoryName(int categoryid, String name) {
        int result = categoryDao.updateCategoryName(categoryid, name);
        if (result <= 0) {
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        } else {
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
        }
    }

    @Override
    public ServerResponse<Set<Category>> findAllSubCategory(int id) {
        Set<Category> setc = categoryDao.findAllSubCategory(id);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), setc, ResponseCode.SUCCESS.getMsg());
    }


}
