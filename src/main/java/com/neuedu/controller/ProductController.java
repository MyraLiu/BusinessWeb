package com.neuedu.controller;

import com.neuedu.pojo.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller   //
//@RestController   // 所有方法的返回值均为 json格式
//配置通用的访问路径
//@RequestMapping(value = "/manage/product")
@RequestMapping(value = "/product")
public class ProductController {

    //配置多条访问路径，以及method访问方式,仅限post
    @RequestMapping(value = {"/add", "/hello"})  //,method = RequestMethod.POST
    // 接受前端传入参数
//    public   String   addproduct(@RequestParam("productid") String id,@RequestParam("name") String name){
//    如果传参的名称与形参的名称相同，可以省略注解不写
    public String addproduct(String productid, String name) {
        System.out.println("====addproduct====" + productid + "   " + name);
        //  /WEB-INF/jsp/addproduct.jsp
        return "addproduct"; //逻辑视图
    }

    //向前端返回值--实现方式1
    @RequestMapping("/find")
    public ModelAndView findProduct(@RequestParam("productid") String productid, ModelAndView modelAndView) {
        Product product = new Product();
        product.setId(100);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("showproduct");
        return modelAndView;
    }


    //向前端返回值--实现方式2
    @RequestMapping("/findbymodel")
    public String findProduct(@RequestParam("productid") int productid, Model model) {
        Product product = new Product();
        product.setId(productid);
        model.addAttribute("product", product);
        return "showproduct";
    }


    //向前端返回值--实现方式2
    @RequestMapping("/findbymap")
    public String findProduct(@RequestParam("productid") int productid, Map<String, Product> map) {
        Product product = new Product();
        product.setId(productid);

        map.put("product", product);
        return "showproduct";
    }


    //获取session request  response
    @RequestMapping("/findbyrequest")
    public String findProduct(@RequestParam("productid") int productid, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        Product product = new Product();
        product.setId(productid);
//request.setAttribute("product",product);
        session.setAttribute("product", product);
        return "showproduct";//如果类设置注解为@RestController ,此处返回的就是字符串不是逻辑视图
    }


    //通过springMVC给前端返回json格式的数据

    @RequestMapping("/findbyjson")
//    @ResponseBody              // 返回格式 {"id":1211211112}
    public Product findProduct(@RequestParam("productid") int productid) {
        Product product = new Product();
        product.setId(productid);
        return product;
    }


    //返回json数组
    @RequestMapping("/findallbyjson")
 @ResponseBody    // 返回格式 [{"id":1211211112}]
    public List<Product> findAllProduct(@RequestParam("productid") int productid) {
        Product product = new Product();
        product.setId(1000);
        List<Product> list = new ArrayList<>();
        list.add(product);
        return list;
    }



    // restful 以资源为导向的请求方式
    @RequestMapping(value = "/{operation}/{productid}")
    @ResponseBody    // 返回格式 [{"id":1211211112}]
    public Product findProductById(@PathVariable("operation") int operation,@PathVariable("productid") int productid) {
        Product product = new Product();
        product.setId(productid);
        System.out.println("====operation ====="+operation);// {"id":2}
        return product;
    }


    @RequestMapping(value="/forward")
    public String forward(){
//        配置请求分派
        System.out.println("===========forward===========");
        return "forward:findallbyjson";
    }
    /*也可以这样定义新的方法
    * public String forward(HttpServletRequest request){
//        配置请求分派
    * */
}
