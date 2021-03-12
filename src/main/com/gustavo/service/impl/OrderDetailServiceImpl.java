package com.gustavo.service.impl;

import com.gustavo.base.BaseDao;
import com.gustavo.base.BaseServiceImpl;
import com.gustavo.mapper.OrderDetailMapper;
import com.gustavo.po.OrderDetail;
import com.gustavo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends  BaseServiceImpl<OrderDetail> implements OrderDetailService {

    @Autowired
    OrderDetailMapper orderDetailMapper;


    @Override
    public BaseDao<OrderDetail> getBaseDao() {
        return orderDetailMapper;
    }
}
