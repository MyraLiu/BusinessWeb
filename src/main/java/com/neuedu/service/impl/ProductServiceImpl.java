package com.neuedu.service.impl;


import com.neuedu.businessconst.Const;
import com.neuedu.common.DateUtils;
import com.neuedu.common.PropertiesUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ICategoryDao;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductService;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public ServerResponse<String> addorupdateProduct(Product p) {
        // not null
        if (p == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(), ResponseCode.NEED_PRODUCT.getMsg());
        }
        // sub_images  1.jpg,2.jpg,3.jpg
        //main_image 从子图中选一个
        String subimages = p.getSub_images();
        if (subimages != null && !subimages.equals("")) {
            String[] images = subimages.split(",");
            if (images != null && images.length > 0) {
                p.setMain_image(images[0]);
            }
        }

        Integer productId = p.getId();
        int result = 0;
        if (productId == null) {
            result = productDao.addProduct(p);

        } else {
            result = productDao.updateProduct(p);
        }
        if (result > 0) {
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
        } else {
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }

    }

    @Override
    public ServerResponse<String> onlineoroffline(Integer id, Integer status) {

        if (id == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(), ResponseCode.NEED_PRODUCT.getMsg());
        }

        if (status == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT_STATUS.getCode(), ResponseCode.NEED_PRODUCT_STATUS.getMsg());
        }
        Product p = new Product();
        p.setId(id);
        p.setStatus(status);
        int result = productDao.updateProduct(p);
        if (result > 0) {
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
        } else {
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }

    }

    @Override
    public ServerResponse<ProductVO> findProductById(Integer productid) {

        if (productid == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(), ResponseCode.NEED_PRODUCT.getMsg());
        }
        Product p = productDao.findProductById(productid);
        ProductVO productVO = assembleProduct(p);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), productVO, ResponseCode.SUCCESS.getMsg());

    }

    @Override
    public ServerResponse<PageModel<ProductVO>> findProductByPageNo(Integer pageNo, Integer pageSize) {
        // 获取查询商品数据
        List<Product> list = productDao.findProductByPageNo(pageNo, pageSize);
        // 将数据转换为vo类显示
        List<ProductVO> listvo = new ArrayList<>();
        for (Product p : list) {
            ProductVO productVO = assembleProduct(p);
            listvo.add(productVO);
        }
        //计算总记录数
        long totalrecord = productDao.findTotalRecord();
        long totalpage = (totalrecord % pageSize == 0) ? totalrecord / pageSize : totalrecord / pageSize + 1;

        // 分页页面输出结构
        PageModel<ProductVO> pageModel = new PageModel<>();
        pageModel.setData(listvo);
        pageModel.setTotalPage(totalpage);
        // 首页和尾页的标志
        if (pageNo == 1) {
            pageModel.setFirst(true);
        } else {
            pageModel.setFirst(false);
        }

        if (pageNo == totalpage) {
            pageModel.setLast(true);
        } else {
            pageModel.setLast(false);
        }

        // 响应信息
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), pageModel, ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<PageModel<ProductVO>> findProductByIdOrName(Integer id, String name, Integer pageNo, Integer pageSize) {

        if (name != null && !name.equals("")) {
            name = "%" + name + "%";
        }

        List<Product> list = productDao.findProductByIdOrName(id, name, pageNo, pageSize);
        System.out.println(list);
        // 将数据转换为vo类显示
        List<ProductVO> listvo = new ArrayList<>();
        for (Product p : list) {
            ProductVO productVO = assembleProduct(p);
            listvo.add(productVO);
        }

        //计算总记录数
        long totalrecord = productDao.findTotalRecord(id, name);
        long totalpage = (totalrecord % pageSize == 0) ? totalrecord / pageSize : totalrecord / pageSize + 1;

        // 分页页面输出结构
        PageModel<ProductVO> pageModel = new PageModel<>();
        pageModel.setData(listvo);
        pageModel.setTotalPage(totalpage);
        // 首页和尾页的标志
        if (pageNo == 1) {
            pageModel.setFirst(true);
        } else {
            pageModel.setFirst(false);
        }

        if (pageNo == totalpage) {
            pageModel.setLast(true);
        } else {
            pageModel.setLast(false);
        }
        // 响应信息
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), pageModel, ResponseCode.SUCCESS.getMsg());

    }

    @Override
    public ServerResponse<String> upload(MultipartFile upload) {

        //重新生成一个文件名，生成一个唯一的字符串UUID+扩展名
        if (upload != null) {
            //1. 获取原文件的扩展名
            String originalFilename = upload.getOriginalFilename();
            if (originalFilename != null && !originalFilename.equals("")) {
                int doti = originalFilename.lastIndexOf(".");/*获取最后一个点的位置索引*/
                String extendname = originalFilename.substring(doti);/*包含点的索引[ 闭区间,开区间），获取到原文件的扩展名*/
                //2. 生成新的文件名
                String uuid = UUID.randomUUID().toString();
                String newFileName = uuid + extendname;
                // 3. 构造新文件
                String filePath = "d:\\ftpfile";/*文件存储的目录*/
                File file = new File(filePath, newFileName);/*构建目录下的新文件*/
                try {
                    /*写入新文件*/
                    upload.transferTo(file);
                    return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), newFileName, ResponseCode.SUCCESS.getMsg());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());

    }

    @Override
    public ServerResponse<ProductVO> findProductDetail(Integer productId) {

        //参数校验
        if (productId == null) {
            return ServerResponse.createServerResponce(ResponseCode.NEED_PRODUCT.getCode(), ResponseCode.NEED_PRODUCT.getMsg());
        }
        // 根据商品id查询商品  判断商品的状态  上架下架
        Product product = productDao.findProductByIdAndOnline(productId);

        //商品不存在或者已经下架
        if (product == null) {
            return ServerResponse.createServerResponce(ResponseCode.PRODUCT_OFFLINE.getCode(), ResponseCode.PRODUCT_OFFLINE.getMsg());
        }
        ProductVO pvo = assembleProduct(product);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), pvo, ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<PageModel<ProductVO>> seatchProducts(Integer categoryId, String productname, Integer pageNo, Integer pageSize,String orderby) {

        //参数校验
       if (categoryId == null && (productname == null||productname.equals(""))) {
            return ServerResponse.createServerResponce(Const.ProductCode.ILLEGAL_PARAM.getCode(), Const.ProductCode.ILLEGAL_PARAM.getMsg());
        }

        Set<Integer> set = new HashSet<>();
        //如果categoryid 不为空,根据categoryid查询类别信息

        if (categoryId != null) {
            Category category = categoryDao.findCategoryById(categoryId);
            // category为空，而且productname也为空  ，没有商品
            if (category == null && (productname == null || productname.equals(""))) {
                // 没有查询到商品
                PageModel<ProductVO> pageModel = new PageModel<>();
                pageModel.setData(null);
                pageModel.setTotalPage(0L);
                pageModel.setCurrentPage(pageNo);
                pageModel.setFirst(false);
                pageModel.setLast(false);
                return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), pageModel, ResponseCode.SUCCESS.getMsg());
            }
            // category 不为空，查询这个类别和子类别下的商品信息，需要递归的查询子类别
            set = categoryService.findAllChildByCategory(set, categoryId);
        }


        //  判断商品名称，如果productname不为空， %productname%

        if (productname != null && !productname.equals("")) {
            productname = "%" + productname + "%";
        }

            // 判断排序字段是否为空
            if(orderby!=null&& !orderby.equals("")){
                String[] split = orderby.split("_");
               if(split!=null && split.length>0){
                   orderby = split[1];
                   System.out.println(orderby);
               }
            }


            List<Product> listp = productDao.findProductsByCategoryIdsAndProductName(set, productname, pageNo, pageSize,orderby);
            // 将数据转换为vo类显示
            List<ProductVO> listvo = new ArrayList<>();
            for (Product p : listp) {
                ProductVO productVO = assembleProduct(p);
                listvo.add(productVO);
            }

            System.out.println(listvo);

            //计算总记录数
            long totalrecord = productDao.findTotalRecord(set, productname);
            long totalpage = (totalrecord % pageSize == 0) ? totalrecord / pageSize : totalrecord / pageSize + 1;

            // 分页页面输出结构
            PageModel<ProductVO> pageModel = new PageModel<>();
            pageModel.setData(listvo);
            pageModel.setTotalPage(totalpage);
            // 首页和尾页的标志
            if (pageNo == 1) {
                pageModel.setFirst(true);
            } else {
                pageModel.setFirst(false);
            }

            if (pageNo == totalpage) {
                pageModel.setLast(true);
            } else {
                pageModel.setLast(false);
            }
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), pageModel, ResponseCode.SUCCESS.getMsg());

        // sql： select  *  from neuedu_product
        // if(categoryids !=null){
        // where categoryid  in (……)
        // }
        // if(productname != null){
        // and name like #{productname}
        // }

    }

    /**
     * 将product这个pojo类转换成vo类显示
     */
    public ProductVO assembleProduct(Product product) {
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
