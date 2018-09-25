package com.neuedu.front;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端商品详情页面
 */
@RestController
@RequestMapping("/product")
public class FrontProductController {
@Autowired
    private  IProductService productService;


    /**
     * 查看商品详情
     * @param productid
     * @return
     */
    @RequestMapping("/find")
    public ServerResponse<ProductVO> detail(Integer productid){

        // spring mvc处理全局的异常---todo
        return productService.findProductDetail(productid);
    }

    /**
     * 前台搜索商品
     * @param categoryid
     * @param productname
     * @param pageno
     * @param pagesize
     * @return
     */

    @RequestMapping("/search")
    public ServerResponse<PageModel<ProductVO>> search(@RequestParam(required = false) Integer categoryid,
                                                       @RequestParam(required = false) String productname,
                                                       @RequestParam(defaultValue = "1")  Integer pageno,
                                                       @RequestParam(defaultValue = "10") Integer pagesize,
                                                        @RequestParam(required = false)String orderby) {

        System.out.println(pageno+" "+pagesize+" " +categoryid+" "+productname);
    return productService.seatchProducts(categoryid,productname,pageno,pagesize,orderby);
    }



}
