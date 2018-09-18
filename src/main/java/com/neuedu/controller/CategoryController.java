package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.CategoryServiceImpl;
import com.neuedu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICategoryService categoryService;


    /**
     * 添加 子类别
     *
     * @param parentid
     * @param categoryname
     */
    @RequestMapping("/add")
    protected ServerResponse<String> addCategory(@RequestParam(name = "parentid", required = true, defaultValue = "0") Integer parentid,
                                                 @RequestParam(name = "categoryname", required = true) String categoryname, HttpSession session) {

        //必须要登录-判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (user == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        //必须要有管理员权限-判断用户是否有权限
        //业务逻辑写到service层
        boolean isAdmin = userService.isAdminRole(user);
        System.out.println(isAdmin);
        if (isAdmin) {
            return categoryService.addCategory(parentid, categoryname);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }

    }


    /**
     * 查询子分类
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("findsubcategory")
    protected ServerResponse<List<Category>> findSubCategory(Integer categoryid,HttpSession session)  {


        if (categoryid == null || categoryid.equals("")) {
            return ServerResponse.createServerResponce(ResponseCode.GETSUBCATEGORY_NEED_CATEGORYID.getCode(), ResponseCode.GETSUBCATEGORY_NEED_CATEGORYID.getMsg());
        }
        //必须要登录-判断用户是否登录
      UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (user == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        //必须要有管理员权限-判断用户是否有权限
        //业务逻辑写到service层
        boolean isAdmin = userService.isAdminRole(user);
        System.out.println(isAdmin);
        if (isAdmin) {
            return categoryService.findSubCategoryById(categoryid);
        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }
    }

    /*

     */

    /**
     * 修改分类节点名称
     *
     * @throws ServletException
     * @throws IOException
     */
@RequestMapping("/updatecategoryname")
    protected ServerResponse<String> updateCategoryName(Integer categoryid, String categoryname,HttpSession session) throws ServletException, IOException {

        if (categoryid == null || categoryid.equals("")) {
            return ServerResponse.createServerResponce(1, "categoryid为必须参数");
        }
        if (categoryname == null || categoryname.equals("")) {
            return ServerResponse.createServerResponce(1, "新名称不能为空");
        }
    //必须要登录-判断用户是否登录
    UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if (user == null) {
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
    }
    //必须要有管理员权限-判断用户是否有权限
    //业务逻辑写到service层
    boolean isAdmin = userService.isAdminRole(user);
    System.out.println(isAdmin);
    if (isAdmin) {
        return categoryService.updateCategoryName(categoryid, categoryname);
    }else{
        return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
    }

    }


    /**
     *
     *
     */
    /**
     * 查询所有分类子节点

     */

    protected ServerResponse<Set<Category>> findAllSubCategory(Integer categoryid, HttpSession session) throws ServletException, IOException {


        if (categoryid == null || categoryid.equals("")) {
            return ServerResponse.createServerResponce(1, "categoryid为必须参数");
        }
        //必须要登录-判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (user == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        //必须要有管理员权限-判断用户是否有权限
        //业务逻辑写到service层
        boolean isAdmin = userService.isAdminRole(user);
        System.out.println(isAdmin);
        if (isAdmin) {
            return categoryService.findAllSubCategory(categoryid);
        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }

    }


}


