package com.neuedu.service.impl;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ICategoryDao;
import com.neuedu.dao.impl.CategoryDaoImpl;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;

import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements ICategoryService {
    ICategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findSubCategoryById(int id) {


        return categoryDao.findSubCategoryById(id);
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
