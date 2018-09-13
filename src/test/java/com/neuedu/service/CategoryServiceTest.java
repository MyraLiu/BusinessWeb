package com.neuedu.service;

import com.neuedu.service.impl.CategoryServiceImpl;
import org.junit.Test;

public class CategoryServiceTest {

    @Test
    public void test(){
        ICategoryService categoryService = new CategoryServiceImpl();
        System.out.println(categoryService.findSubCategoryById(100032));
    }
}
