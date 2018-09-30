package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import com.neuedu.service.IUserService;
import com.neuedu.vo.OrderItemVO;
import com.neuedu.vo.OrderVO;
import com.neuedu.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/order")
public class BackOrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/send")
    public ServerResponse sendOrder(Long orderno, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        // 判断用户是否登录
        if(userInfo==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        // 判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
           return orderService.sendOrder(orderno);

        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }
    }


    @RequestMapping("/listAllOrder")
    public ServerResponse<PageModel<OrderVO<OrderItemVO>>> listOrder(@RequestParam(required = false ,defaultValue = "1") Integer pagenum,
                                                                     @RequestParam(required = false,defaultValue = "10")Integer pagesize,
                                                                     HttpSession session){

        // 校验用户是否登录
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }


        // 判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            return orderService.listOrder(pagenum,pagesize,userInfo.getId());

        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());
        }

    }

    @RequestMapping("/detail")
    public ServerResponse detail(Long orderno,HttpSession session){

        // 校验用户是否登录
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }


        // 判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            return orderService.getOrderDetail(orderno);

        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());
        }
    }



    @RequestMapping("/search")
    public ServerResponse search(Long orderno,HttpSession session){

        // 校验用户是否登录
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        // 判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            return orderService.searchOrder(orderno);

        }else{
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());
        }
    }

}
