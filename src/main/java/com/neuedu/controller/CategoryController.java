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
@RestController
@RequestMapping("/category")
public class CategoryController {
//@Autowired
    private IUserService userService=new UserServiceImpl();
@Autowired
    private ICategoryService categoryService;




    /**
     * 添加 子类别
     * @param parentid
     * @param categoryname
     */
        @RequestMapping("/add")
        protected ServerResponse<String> addCategory(@RequestParam(name="parentid" ,required = true ,defaultValue = "0")Integer parentid,
                                   @RequestParam(name="categoryname" ,required = true) String categoryname,HttpSession session) {

            ServerResponse<String> stringServerResponse= categoryService.addCategory(parentid,categoryname);
            System.out.println("=========servres=="+stringServerResponse);
            return stringServerResponse;
            //必须要登录-判断用户是否登录
            //03-06:41
      /*      UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
            if (user == null) {
                return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
            }
            //必须要有管理员权限-判断用户是否有权限
            //业务逻辑写到service层
            if (userService.isAdminRole(user)) {
                //有管理员权限，可以添加类别
                return categoryService.addCategory(parentid,categoryname);
            } else {
                // 03-14:29
                 return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());
            }*/

        }


    /**
     * 查询子分类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void findSubCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String categoryid = request.getParameter("categoryid");
        HttpSession session = request.getSession();
        if(categoryid==null||categoryid.equals("")){
            ServerResponse sr= ServerResponse.createServerResponce(1,"categoryid为必须参数");
            ServerResponse.convert2Json(sr,response);
            return;
        }

        ICategoryService cs = new CategoryServiceImpl();
        try {
            int _categoryid = Integer.parseInt(categoryid);
            List<Category> categorylist = cs.findSubCategoryById(_categoryid);
            ServerResponse sr= ServerResponse.createServerResponce(0,categorylist,"数据获取成功");
            ServerResponse.convert2Json(sr,response);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

/*

        */
/**
         * 修改分类节点名称
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */

        protected void updateCategoryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String categoryid = request.getParameter("categoryid");
            String categoryname = request.getParameter("categoryname");
            HttpSession session = request.getSession();
            if(categoryid==null||categoryid.equals("")){
                ServerResponse sr= ServerResponse.createServerResponce(1,"categoryid为必须参数");
                ServerResponse.convert2Json(sr,response);
                return;
            }
            if(categoryname==null||categoryname.equals("")){
                ServerResponse sr= ServerResponse.createServerResponce(1,"新名称不能为空");
                ServerResponse.convert2Json(sr,response);
                return;
            }


            try {
                int _categoryid = Integer.parseInt(categoryid);
                int result =categoryService.addCategory1(_categoryid,categoryname);
                if(result>0) {
                    ServerResponse sr = ServerResponse.createServerResponce(0,"分类名称修改成功");
                    ServerResponse.convert2Json(sr, response);
                }else{
                    ServerResponse sr = ServerResponse.createServerResponce(1, "分类名称修改失败");
                    ServerResponse.convert2Json(sr, response);
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }


        /**
         *
         *
         */
        /**
         * 查询所有分类子节点
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */

        protected void findAllSubCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String categoryid = request.getParameter("categoryid");
            HttpSession session = request.getSession();
            if(categoryid==null||categoryid.equals("")){
                ServerResponse sr= ServerResponse.createServerResponce(1,"categoryid为必须参数");
                ServerResponse.convert2Json(sr,response);
                return;
            }

            ICategoryService cs = new CategoryServiceImpl();
            try {
                int _categoryid = Integer.parseInt(categoryid);
                List<Category> categorylist = cs.findSubCategoryById(_categoryid);
                ServerResponse sr= ServerResponse.createServerResponce(0,categorylist,"数据获取成功");
                ServerResponse.convert2Json(sr,response);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }



    }


