package com.neuedu.service.impl;

import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private  IProductDao productDao;


    @Override
    public ServerResponse<String> addorupdateProduct(Product p) {
        // not null
       if(p == null){
           return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
       }
       // sub_images  1.jpg,2.jpg,3.jpg
        //main_image 从子图中选一个
        String subimages = p.getSub_images();
       if(subimages !=null && !subimages.equals("")){
           String[] images = subimages.split(",");
           if(images !=null && images.length>0){
               p.setMain_image(images[0]);
           }
       }

        Integer productId = p.getId();
        int result=0;
       if(productId == null){
            result = productDao.addProduct(p);

       }else{
           result = productDao.updateProduct(p);
       }
        if(result>0){
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else{
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }

    }

    @Override
    public ServerResponse<String> onlineoroffline(Integer id, Integer status) {

        if(id==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());

        }

        if(status==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT_STATUS.getCode(),ResponseCode.NEED_PRODUCT_STATUS.getMsg());

        }
        Product p = new Product();
        p.setId(id);
        p.setStatus(status);
        int result = productDao.updateProduct(p);
        if(result>0){
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else{
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }

    }
}
