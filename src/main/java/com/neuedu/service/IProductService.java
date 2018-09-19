package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;

public interface IProductService {

    /**
     * 添加或更新商品
     */
    ServerResponse<String> addorupdateProduct(Product p);


//    商品上下架
    ServerResponse<String> onlineoroffline(Integer id,Integer status);
}
