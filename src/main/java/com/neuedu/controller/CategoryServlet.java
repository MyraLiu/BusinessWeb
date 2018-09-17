package com.neuedu.controller;

import com.neuedu.common.ServerResponse;
import com.neuedu.exception.BusinesseLoginException;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/cate.do")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf8");
        String operation = request.getParameter("operation");
        if(operation==null||operation.equals("")){
            ServerResponse sr = ServerResponse.createServerResponce(1,"operation参数必须");
            ServerResponse.convert2Json(sr,response);
        }

        if(operation.equals("1")){// 查询子节点
            findSubCategory(request,response);
        }else  if(operation.equals("2")){// 添加子节点
            addCategory(request,response);
        }else  if(operation.equals("3")){// 修改分类名称
            updateCategoryName(request,response);
        }else  if(operation.equals("4")){
            findSubCategory(request,response);
        }



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


    /**
     * 添加子分类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String parentid = request.getParameter("parentid");
        String categoryname = request.getParameter("categoryname");
        HttpSession session = request.getSession();
        if(parentid==null||parentid.equals("")){
            ServerResponse sr= ServerResponse.createServerResponce(1,"parentid为必须参数");
            ServerResponse.convert2Json(sr,response);
            return;
        }
        if(categoryname==null||categoryname.equals("")){
            ServerResponse sr= ServerResponse.createServerResponce(1,"新分类名称不能为空");
            ServerResponse.convert2Json(sr,response);
            return;
        }

        ICategoryService cs = new CategoryServiceImpl();
        try {
            int _parentid = Integer.parseInt(parentid);
            int result =cs.addCategory1(_parentid,categoryname);
            if(result>0) {
                ServerResponse sr = ServerResponse.createServerResponce(0,"新分类添加成功");
                ServerResponse.convert2Json(sr, response);
            }else{
                ServerResponse sr = ServerResponse.createServerResponce(1, "新分类添加失败");
                ServerResponse.convert2Json(sr, response);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }


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

        ICategoryService cs = new CategoryServiceImpl();
        try {
            int _categoryid = Integer.parseInt(categoryid);
            int result =cs.addCategory1(_categoryid,categoryname);
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
