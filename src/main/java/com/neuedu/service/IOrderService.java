package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface IOrderService  {
    ServerResponse pay(Long orderid, Integer userid);

    void queryOrderPayStatus(Long orderid);

    void callback(Long orderid);
}
