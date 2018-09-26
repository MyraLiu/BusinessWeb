package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/pay.do")
    public ServerResponse<Object> pay(Long orderid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }

      return  orderService.pay(orderid,userInfo.getId());

    }

/*/order/query_order_pay_status.do*/
    public ServerResponse<Boolean> queryOrderPayStatus(Long orderid,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        orderService.queryOrderPayStatus(orderid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());

    }


    @RequestMapping("/callback.do")
    public void alipay_callback(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("====callback.do=======");
    }
}
