package com.neuedu.controller;

import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CateController {
//以下为老师测试代码，暂时取消
/*    @Autowired
    ICategoryService categoryService;
    @RequestMapping(value = "/find")
    public void test(@RequestParam("id") Integer id){

        List<Category> category=categoryService.findSubCategoryById(id);
        System.out.println("==="+category);
    }*/

}
