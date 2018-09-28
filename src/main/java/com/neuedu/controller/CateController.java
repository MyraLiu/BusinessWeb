package com.neuedu.controller;


import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICartService;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/cart")
public class CateController {
    @Autowired
   private ICartService cartService;


    /**
     * 添加商品到购物车
     * @param productid
     * @param count
     * @param session
     * @return
     */
    @RequestMapping("/add")
    public ServerResponse<CartVO> add(Integer productid, Integer count, HttpSession session){
        //1. 判定用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.addProductToCart(user.getId(),productid,count);


    }



    // http://localhost:8080/cart/update.do?productId=1&count=2

    /**
     * 修改购物车中商品信息
     * @param productid
     * @param count
     * @param session
     * @return
     */
    @RequestMapping("/update")
    public ServerResponse<CartVO> update(Integer productid, Integer count, HttpSession session){
        //1. 判定用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.updateProductInCart(user.getId(),productid,count);


    }


    // http://localhost:8080/cart/delete_product.do?productIds=1,3

    /**
     * 删除购物车中的商品
     * @param productids
     * @param session
     * @return
     */
    @RequestMapping("/remove")
    public ServerResponse<CartVO> remove(Integer[] productids, HttpSession session){
        //1. 判定用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.removeProduct(productids,user.getId());

    }


    // http://localhost:8080/cart/select.do?productId=1

    /**
     * 选中商品
     * @param productid
     *
     * @param session
     * @return
     */
    @RequestMapping("/select")
    public ServerResponse<CartVO> select(Integer productid, HttpSession session){
        //1. 判定用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.checkProduct(productid,user.getId());

    }
// http://localhost:8080/cart/un_select.do?productId=2

    /**
     * 取消选种商品
     * @param productid
     * @param session
     * @return
     */
    @RequestMapping("/unselect")
    public ServerResponse<CartVO> unselect(Integer productid, HttpSession session){
        //1. 判定用户是否登录

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.uncheckProduct(productid,user.getId());

    }



    // http://localhost:8080/cart/get_cart_product_count.do
    @RequestMapping("/count")
    public ServerResponse<Integer> getCount(HttpSession session){
        //1. 判定用户是否登录

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.sumProducts(user.getId());

    }

// http://localhost:8080/cart/select_all.do
    @RequestMapping("/selectall")
    public ServerResponse<CartVO> selectAll(HttpSession session){
        //1. 判定用户是否登录

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.selectAll(user.getId());

    }


   //  http://localhost:8080/cart/un_select_all.do
    @RequestMapping("/selectnone")
    public ServerResponse<CartVO> unselectAll(HttpSession session){
        //1. 判定用户是否登录

        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        return cartService.selectNone(user.getId());

    }


//    http://localhost:8080/cart/list.do
@RequestMapping("/list")
public ServerResponse<CartVO> list(HttpSession session){
    //1. 判定用户是否登录
    UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if(user==null){
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
    }

    return cartService.listCart(user.getId());


}



}
