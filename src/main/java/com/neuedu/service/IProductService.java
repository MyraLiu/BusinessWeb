package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {

    /**
     * 添加或更新商品
     */
    ServerResponse<String> addorupdateProduct(Product p);


//    商品上下架
    ServerResponse<String> onlineoroffline(Integer id,Integer status);

//    查询商品详情接口
    ServerResponse<ProductVO> findProductById(Integer productid);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageModel<ProductVO>> findProductByPageNo(Integer pageNo, Integer pageSize);

    /**
     * 后台模糊查询
     * @param id
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServerResponse<PageModel<ProductVO>> findProductByIdOrName(Integer id, String name,Integer pageNo, Integer pageSize);


    /**
     * 图片上传
     * @param upload
     * @return
     */
    ServerResponse<String> upload(MultipartFile upload);

    /**
     * 前台查询商品详情
     * @param productId
     * @return
     */
    public ServerResponse<ProductVO> findProductDetail(Integer productId);



    ServerResponse<PageModel<ProductVO>> seatchProducts(Integer categoryId,String productname,Integer pageNo,Integer pageSize,String orderby);
}
