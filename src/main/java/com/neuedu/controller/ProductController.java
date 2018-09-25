package com.neuedu.controller;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductService;
import com.neuedu.service.IUserService;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ServerResponse<String> addProduct(Product product,HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (userService.isAdminRole(userInfo)) {
            return productService.addorupdateProduct(product);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }

    }


    /**
     * 商品上下架
     */
    @RequestMapping("/onlineoroffline")
    public ServerResponse<String> onlineoroffline(Integer productid, Integer status, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (userService.isAdminRole(userInfo)) {
            return productService.onlineoroffline(productid, status);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }
    }


    /**
     * 商品详情
     */
    @RequestMapping("/detail")
    public ServerResponse<ProductVO> detail(Integer productid, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (userService.isAdminRole(userInfo)) {
        return productService.findProductById(productid);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }
    }

    /**
     *
     * @param pageno
     * @param pagesize
     * @param session
     * @return
     */
    @RequestMapping("/findproductbypageno")
    public ServerResponse<PageModel<ProductVO>> findProductByPageNo(@RequestParam(defaultValue = "1")Integer pageno, @RequestParam(defaultValue = "1")Integer pagesize, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (userService.isAdminRole(userInfo)) {
            return productService.findProductByPageNo(pageno,pagesize);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }
    }


    /**
     * 分页搜索商品
     * @param pageno
     * @param pagesize
     * @param session
     * @return
     */
    @RequestMapping("/search")
    public ServerResponse<PageModel<ProductVO>> search(@RequestParam(required = false)Integer productid,
                                                       @RequestParam(required = false)String productname,
                                                       @RequestParam(defaultValue = "1")Integer pageno,
                                                       @RequestParam(defaultValue = "1")Integer pagesize,
                                                       HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (userService.isAdminRole(userInfo)) {
            return productService.findProductByIdOrName(productid,productname,pageno,pagesize);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }
    }



    @RequestMapping("/upload")
    public ServerResponse<String> upload(MultipartFile upload, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (userService.isAdminRole(userInfo)) {
            return productService.upload(upload);
        } else {
            return ServerResponse.createServerResponce(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getMsg());
        }
    }


}
