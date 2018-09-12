package com.neuedu.dao;

import com.neuedu.pojo.Category;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface ICategoryDao {
    /**
    * 查询类别信息
    * */

    Category findCategoryById(int id);
/**
 * 获取品类的子节点（平级）
 *
* */
    List<Category> findSubCategoryById(int id);
/**
 * 添加结点
 *
 */
int addCategory(int parent_id,String name);

/**
 * 修改分类名称
 */
int updateCategoryName(int categoryid,String newname);



    /**
     * 获取品类的所有子节点
     * */
Set<Category>  findAllSubCategory(int id);
}
