package com.neuedu.service.impl;

import com.neuedu.common.DateUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IShippingDao;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IShippingService;
import com.neuedu.vo.ShippingListVO;
import com.neuedu.vo.ShippingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private IShippingDao shippingDao;

    @Override
    public ServerResponse<String> add(Shipping shipping) {
        // 非空判断
        if (shipping.getUser_id() == null || shipping.getReceiver_name() == null
                || (shipping.getReceiver_mobile() == null && shipping.getReceiver_phone() == null)
                || shipping.getReceiver_province() == null
                || shipping.getReceiver_address() == null) {
            return ServerResponse.createServerResponce(ResponseCode.SHIPPING_NEED_MESSAGE.getCode(), ResponseCode.SHIPPING_NEED_MESSAGE.getMsg());
        }

        // 添加地址信息
        int result = shippingDao.add(shipping);

        if (result > 0) {
           Integer shippingid = shippingDao.findId(shipping);
            String s = "shippingId : "+shippingid;
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), s, ResponseCode.SUCCESS.getMsg());
        } else {
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ServerResponse<String> remove(Integer shippingId) {
        // 非空判断
        if(shippingId==null || shippingId.equals("")){
            return ServerResponse.createServerResponce(ResponseCode.NEED_SHIPPINGID.getCode(),ResponseCode.NEED_SHIPPINGID.getMsg());
        }

        int result =shippingDao.remove(shippingId);
        if(result>0) {
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
        }else{
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ServerResponse<String> update(Shipping shipping) {
        // 非空判断
        if(shipping==null || shipping.getId()==null){
            return ServerResponse.createServerResponce(ResponseCode.NEED_SHIPPINGID.getCode(),ResponseCode.NEED_SHIPPINGID.getMsg());
        }

        int result =shippingDao.update(shipping);
        if(result>0) {
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
        }else{
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ServerResponse<ShippingVO> find(Integer shippingId) {
       if(shippingId==null||shippingId.equals("")){
           return ServerResponse.createServerResponce(ResponseCode.NEED_SHIPPINGID.getCode(),ResponseCode.NEED_SHIPPINGID.getMsg());
       }
        Shipping shipping =shippingDao.find(shippingId);
        if(shipping!=null) {
            ShippingVO shippingVO = assembleVO(shipping);
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),shippingVO, ResponseCode.SUCCESS.getMsg());
        }else{
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ServerResponse<ShippingListVO<ShippingVO>> list(Integer userid,Integer pageNum,Integer pageSize,String orderby) {

        Integer sortby = null;
        if(orderby!=null&&!orderby.equals("")){
            String[] order = orderby.split("_");
            if(order!=null&&order.length>0){
                if(order[0].equals("name")&&order[1].equals("asc")){
                    sortby=101;
                }else   if(order[0].equals("name")&&order[1].equals("desc")){
                    sortby=102;
                }
            }
        }

      List<Shipping> listshipping = shippingDao.list(userid,pageNum,pageSize,sortby);
      List<ShippingVO> listvo = new ArrayList<>();
      for(Shipping s:listshipping){
          ShippingVO svo = assembleVO(s);
          listvo.add(svo);
      }
      ShippingListVO<ShippingVO>  slvo = new ShippingListVO<>();
      slvo.setList(listvo);
      int count = shippingDao.count(userid);
      slvo.setSize(count);
      slvo.setTotal(count);
      Integer pages;
      if(count%pageSize==0){
          pages=count%pageSize;
      }else{
          pages=count%pageSize+1;
      }
      slvo.setPages(pages);
        slvo.setFirstPage(1);
      slvo.setLastPage(pages);
      if(pageNum==1){
          slvo.setPrePage(0);
          slvo.setFirstPage(true);
          slvo.setHasPreviousPage(false);
      }else{
          slvo.setPrePage(pageNum-1);
          slvo.setFirstPage(false);
          slvo.setHasPreviousPage(true);
      }

      if(pageNum==pages){
          slvo.setNextPage(pages);
          slvo.setLastPage(true);
          slvo.setHasNextPage(false);
      }else{
          slvo.setNextPage(pageNum+1);
          slvo.setLastPage(false);
          slvo.setHasNextPage(true);
      }


        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),slvo,ResponseCode.SUCCESS.getMsg());
    }


    public ShippingVO assembleVO(Shipping shipping) {
        ShippingVO shippingVO = new ShippingVO();
        shippingVO.setId(shipping.getId());
        shippingVO.setUser_id(shipping.getUser_id());
        shippingVO.setReceiver_name(shipping.getReceiver_name());
        shippingVO.setReceiver_phone(shipping.getReceiver_phone());
        shippingVO.setReceiver_mobile(shipping.getReceiver_mobile());
        shippingVO.setReceiver_province(shipping.getReceiver_province());
        shippingVO.setReceiver_city(shipping.getReceiver_city());
        shippingVO.setReceiver_district(shipping.getReceiver_district());
        shippingVO.setCreate_time(DateUtils.dateToString(shipping.getCreate_time()));
        if(shipping.getUpdate_time()!=null) {
            shippingVO.setUpdate_time(DateUtils.dateToString(shipping.getUpdate_time()));
        }
        return shippingVO;
    }



}
