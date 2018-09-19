package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductService;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;

    /**
     * 添加或者更新商品
     */
    @RequestMapping("/add")
    public ServerResponse<String> addProduct(Product product){

        return productService.addorupdateProduct(product);
    }


    /**
     * 商品上下架*/
    @RequestMapping("/onlineoroffline")
    public ServerResponse<String> onlineoroffline(Integer productid, Integer status, HttpSession session){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if(userService.isAdminRole(userInfo)){
            return productService.onlineoroffline(productid,status);
        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());
        }
    }
}
