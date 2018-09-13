package com.neuedu;

import com.neuedu.dao.ICategoryDao;
import com.neuedu.dao.IProductDao;
import com.neuedu.dao.impl.CategoryDaoImpl;
import com.neuedu.dao.impl.ProductDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryDaoTest {

    ICategoryDao categoryDao =null;

    @Before
    public void beforetest(){
        categoryDao=   new CategoryDaoImpl();
    }

    @Test
    public void testFindById(){

        System.out.println(categoryDao.findCategoryById(100101));
    }

    @Test
    public  void testFindSubCategoryById(){
        System.out.println(categoryDao.findSubCategoryById(100032));
    }



    @Test
    public  void testAddCategory(){
        System.out.println(categoryDao.addCategory(100032,"家用电器"));
    }


    @Test
    public  void testUpdateCategoryName(){
        System.out.println(categoryDao.updateCategoryName(100107,"鼠标"));
    }

    @Test
    public void testfindAllSub(){
        System.out.println(categoryDao.findAllSubCategory(100032));
    }


    @After
    public  void afterTest(){
        categoryDao=null;
    }




}
