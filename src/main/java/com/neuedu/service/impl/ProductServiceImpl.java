package com.neuedu.service.impl;


import com.neuedu.common.DateUtils;
import com.neuedu.common.PropertiesUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ServerResponse<ProductVO> findProductById(Integer productid) {

        if(productid==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        Product p = productDao.findProductById(productid);
        ProductVO productVO = assembleProduct(p);
        return  ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),productVO,ResponseCode.SUCCESS.getMsg());

    }

    @Override
    public ServerResponse<PageModel<ProductVO>> findProductByPageNo(Integer pageNo, Integer pageSize) {
       // 获取查询商品数据
        List<Product> list = productDao.findProductByPageNo(pageNo,pageSize);
       // 将数据转换为vo类显示
        List<ProductVO> listvo = new ArrayList<>();
        for (Product p:list) {
            ProductVO productVO = assembleProduct(p);
            listvo.add(productVO);
        }
        //计算总记录数
       long totalrecord = productDao.findTotalRecord();
       long totalpage = (totalrecord%pageSize==0)?totalrecord/pageSize:totalrecord/pageSize+1;

       // 分页页面输出结构
       PageModel<ProductVO> pageModel = new PageModel<>();
        pageModel.setData(listvo);
        pageModel.setTotalPage(totalpage);
        // 首页和尾页的标志
        if(pageNo == 1){
            pageModel.setFirst(true);
        }else{
            pageModel.setFirst(false);
        }

        if(pageNo==totalpage){
            pageModel.setLast(true);
        }else{
            pageModel.setLast(false);
        }

        // 响应信息
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),pageModel,ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<PageModel<ProductVO>> findProductByIdOrName(Integer id, String name, Integer pageNo, Integer pageSize) {

        if(name!=null&&!name.equals("")){
            name="%"+name+"%";
        }

        List<Product> list = productDao.findProductByIdOrName(id,name,pageNo,pageSize);
        System.out.println(list);
        // 将数据转换为vo类显示
        List<ProductVO> listvo = new ArrayList<>();
        for (Product p:list) {
            ProductVO productVO = assembleProduct(p);
            listvo.add(productVO);
        }

        //计算总记录数
        long totalrecord = productDao.findTotalRecord(id,name);
        long totalpage = (totalrecord%pageSize==0)?totalrecord/pageSize:totalrecord/pageSize+1;

        // 分页页面输出结构
        PageModel<ProductVO> pageModel = new PageModel<>();
        pageModel.setData(listvo);
        pageModel.setTotalPage(totalpage);
        // 首页和尾页的标志
        if(pageNo == 1){
            pageModel.setFirst(true);
        }else{
            pageModel.setFirst(false);
        }

        if(pageNo==totalpage){
            pageModel.setLast(true);
        }else{
            pageModel.setLast(false);
        }
        // 响应信息
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),pageModel,ResponseCode.SUCCESS.getMsg());

    }

    /**
     * 将product这个pojo类转换成vo类显示
     * */
    public ProductVO assembleProduct(Product product){
        ProductVO pvo = new ProductVO();
        pvo.setId(product.getId());
        pvo.setName(product.getName());
        pvo.setCategory_id(product.getCategory_id());
        pvo.setDetail(product.getDetail());
        pvo.setSubtitle(product.getSubtitle());
        pvo.setMain_image(product.getMain_image());
        pvo.setSub_images(product.getSub_images());
        pvo.setStatus(product.getStatus());
        pvo.setStock(product.getStock());
        pvo.setPrice(product.getPrice());
        pvo.setCreate_time(DateUtils.dateToString(product.getCreate_time()));
        pvo.setUpdate_time(DateUtils.dateToString(product.getUpdate_time()));

        pvo.setImageHost(PropertiesUtils.getProperty("imageHost"));


        return pvo;
    }
}
