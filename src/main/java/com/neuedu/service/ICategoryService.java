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
    ServerResponse<List<Category>> findSubCategoryById(int id);
    /**
     * 添加分类
     * */
    ServerResponse<String> addCategory(int parent_id,String name);

    /**
     * 修改品类的名字
     * */
    ServerResponse<String> updateCategoryName(int categoryid,String name);

    /**
     * 查询全部子分类的id
     */
    ServerResponse<Set<Category>> findAllSubCategory(int id);
    int addCategory1(int parent_id, String name);
}
