package com.neuedu.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.mysql.fabric.Server;
import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Order;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import com.neuedu.vo.OrderItemVO;
import com.neuedu.vo.OrderVO;
import com.neuedu.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class FrontOrderController {
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
@RequestMapping("/querystatus.do")
    public ServerResponse<Boolean> queryOrderPayStatus(Long orderid,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
       return orderService.queryOrderPayStatus(orderid);

    }


    @RequestMapping("/callback.do")
    public String alipay_callback(HttpServletRequest request) {
        System.out.println("====callback.do=======");
        // 获取参数集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String ,String>  params = new HashMap<>();

        for(Iterator<String> iterator= parameterMap.keySet().iterator();iterator.hasNext();){

            String key =iterator.next();
            String[] values= parameterMap.get(key);
            String value = "";
            if(values!=null&& values.length>0){
                for(int i=0;i<values.length;i++){
                    value=(i==values.length-1)?value+values[i]:value+values[i]+",";
                }
            }
            params.put(key,value);
        }

        // 支付宝验签
        params.remove("sign_type");
        System.out.println(params.get("sign_type"));
        try {
            boolean b = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());
            if(b){
                System.out.println("验签成功");
                return orderService.callback(params);
            }else{
                System.out.println("验签失败");
                return "fail";
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return "fail";

}

    /**
     * 创建订单
     * @param shippingid
     * @param session
     * @return
     */
    @RequestMapping("/create.do")
public ServerResponse createOrder(Integer shippingid,HttpSession session){

   // 校验用户是否登录
    UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if (userInfo == null) {
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
    }

    // 创建订单
    return orderService.createOrder(shippingid,userInfo.getId());

}

    /**
     * 取消订单
     * @param orderno
     * @param session
     * @return
     */
    @RequestMapping("/cancelorder.do")
public ServerResponse cancelOrder(Long orderno,HttpSession session){
    // 校验用户是否登录
    UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if (userInfo == null) {
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
    }
    return orderService.cancelOrder(orderno,userInfo.getId());
}

    /**
     * 获取订单详情
     * @param orderno
     * @param session
     * @return
     */
    @RequestMapping("/getOrderDetail.do")
public ServerResponse getOrderDetail(Long orderno,HttpSession session){
    // 校验用户是否登录
    UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if (userInfo == null) {
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
    }
    return orderService.getOrderDetail(orderno,userInfo.getId());
}

    /**
     * 获取购物车中勾选的商品信息
     * @param session
     * @return
     */

@RequestMapping("/getCartProductInfo.do")
public ServerResponse getCartProductInfo(HttpSession session){
    // 校验用户是否登录
    UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if (userInfo == null) {
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
    }
    return orderService.getCartProductInfo(userInfo.getId());

}

    /**
     * 订单列表
     * @param pagenum
     * @param pagesize
     * @param session
     * @return
     */
    @RequestMapping("/listOrder.do")
public ServerResponse<PageModel<OrderVO<OrderItemVO>>> listOrder(@RequestParam(required = false ,defaultValue = "1") Integer pagenum,
                                                                 @RequestParam(required = false,defaultValue = "10")Integer pagesize,
                                                                 HttpSession session){
    // 校验用户是否登录
    UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
    if (userInfo == null) {
        return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
    }
    return orderService.listOrder(pagenum,pagesize,userInfo.getId());
}
}
