package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.vo.ShippingListVO;
import com.neuedu.vo.ShippingVO;

public interface IShippingService {
    /**
     * 添加地址信息
     * @param shipping
     * @return
     */
    ServerResponse<Integer> add(Shipping shipping);

    /**
     * 删除地址信息
     * @param shippingId
     * @return
     */
    ServerResponse<String> remove(Integer shippingId);

    /**
     * 修改地址信息
     * @param shipping
     * @return
     */
    ServerResponse<String> update(Shipping shipping);

    /**
     * 查询地址信息
     * @param shippingId
     * @return
     */
    ServerResponse<ShippingVO> find(Integer shippingId,Integer userid);
    ServerResponse<ShippingVO> find(Integer shippingId);
    /**
     * 查询用户的所有地址
     * @param userid
     * @return
     */
    ServerResponse<ShippingListVO<ShippingVO>> list(Integer userid, Integer pageNum, Integer pageSize, String orderby);
    ServerResponse<ShippingListVO<ShippingVO>> listAll( Integer pageNum, Integer pageSize, String orderby);


    /**
     * 获取shippingvo
     * @param shipping
     * @return
     */
    ShippingVO assembleVO(Shipping shipping);
}
