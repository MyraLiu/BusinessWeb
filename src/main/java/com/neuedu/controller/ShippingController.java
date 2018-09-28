package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IShippingService;
import com.neuedu.vo.ShippingListVO;
import com.neuedu.vo.ShippingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    private IShippingService shippingService;

    @RequestMapping("/add")
    public ServerResponse<Integer> add(Shipping shipping,HttpSession session) {

        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null || !user.getId().equals(shipping.getUser_id())){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }

        // 添加地址
       return shippingService.add(shipping);

    }

@RequestMapping("/delete")
    public ServerResponse<String> delete(Integer shippingid ,HttpSession session){
    // 判断用户是否登录
    UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if(user==null){
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
    }
    // 添加地址
    return shippingService.remove(shippingid);
    }


@RequestMapping("/update")
    public ServerResponse<String> update(Shipping shipping,HttpSession session){
    // 判断用户是否登录
    UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if(user==null){
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
    }
    // 修改地址
    return shippingService.update(shipping);
    }



    @RequestMapping("/find")
    public ServerResponse<ShippingVO> find(Integer shippingid ,HttpSession session){
        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        // 查询地址信息
        return shippingService.find(shippingid);
    }

    @RequestMapping("/list")
    public ServerResponse<ShippingListVO<ShippingVO>> list(Integer userid,@RequestParam(required = false ,defaultValue = "1") Integer pageNum,
                                                           @RequestParam(required = false ,defaultValue = "10") Integer pageSize,
                                                           @RequestParam(required = false ) String orderby,HttpSession session){
        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(user==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        // 查询地址信息
        return shippingService.list(userid,pageNum,pageSize,orderby);
    }

}
