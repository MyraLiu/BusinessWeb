package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface ICategoryService {


    /**
     * 查询子分类
     * */
    List<Category> findSubCategoryById(int id);
    /**
     * 添加分类
     * */
    int addCategory(int parent_id,String name);

    /**
     * 修改品类的名字
     * */
    int updateCategoryName(int categoryid,String name);

    /**
     * 查询全部子分类的id
     */
    ServerResponse findAllSubCategory(int id);

}
