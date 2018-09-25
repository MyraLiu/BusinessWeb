package com.neuedu.dao;

import com.neuedu.pojo.Shipping;

import java.util.List;

public interface IShippingDao  {

    /**
     * 添加地址
     * @param shipping
     * @return
     */
    Integer add(Shipping shipping);

    /**
     * 修改地址
     * @param shipping
     * @return
     */
    Integer update(Shipping shipping);

    /**
     * 删除地址
     * @param shippingid
     * @return
     */
    Integer remove(Integer shippingid);

    /**
     * 地址详细
     * @param shippingid
     * @return
     */
    Shipping find(Integer shippingid);

    /**
     * 地址清单
     * @param userid
     * @return
     */
    List<Shipping> list(Integer userid,Integer pageNum,Integer pageSize,Integer orderby);

    /**
     * 获取对应的id
     * @param shipping
     * @return
     */
    Integer findId(Shipping shipping);

    /**
     * 获取地址个数
     * @param userid
     * @return
     */
    Integer count(Integer userid);
}
